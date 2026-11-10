import sys,struct,gzip,io,json,re
W=r'B:\IdeaProjects\Nukkit-blocks'
R=W+r'\src\main\resources'
J=W+r'\src\main\java\cn\nukkit\block'
# spec: name -> (ClassName, display, base)
spec=json.load(open(sys.argv[1]))
start=int(sys.argv[2])
def u16(n):return struct.pack('>H',n)
def s(x):b=x.encode();return u16(len(b))+b
class P:
    def __init__(s_,d):s_.d=d;s_.p=0
    def r(s_,n):v=s_.d[s_.p:s_.p+n];s_.p+=n;return v
    def val(s_,t):
        if t==1:s_.p+=1
        elif t==2:s_.p+=2
        elif t in(3,5):s_.p+=4
        elif t in(4,6):s_.p+=8
        elif t==7:n=struct.unpack('>i',s_.r(4))[0];s_.p+=n
        elif t==8:n=struct.unpack('>H',s_.r(2))[0];s_.p+=n
        elif t==9:
            et=s_.r(1)[0];n=struct.unpack('>i',s_.r(4))[0]
            for _ in range(n):s_.val(et)
        elif t==10:
            while True:
                tt=s_.r(1)[0]
                if tt==0:return
                n=struct.unpack('>H',s_.r(2))[0];s_.r(n);s_.val(tt)
        elif t==11:n=struct.unpack('>i',s_.r(4))[0];s_.p+=4*n
    def entry(s_):
        """parse top compound of palette entry; return dict name->(type,start,end) spans of payload"""
        out={};
        while True:
            tt=s_.r(1)[0]
            if tt==0:return out
            n=struct.unpack('>H',s_.r(2))[0];k=s_.r(n).decode();a=s_.p;s_.val(tt);out[k]=(tt,a,s_.p)
# --- Data palette
raw=gzip.open(r'B:\IdeaProjects\Data\block_palette.nbt').read()
p=P(raw);assert p.r(1)[0]==10;p.r(2+struct.unpack('>H',raw[1:3])[0]-0 if False else 0)
n0=struct.unpack('>H',raw[1:3])[0];p.p=3+n0
assert raw[p.p]==9 or True
# root compound: find 'blocks' list
tt=p.r(1)[0];n=struct.unpack('>H',p.r(2))[0];assert p.r(n)==b'blocks';et=p.r(1)[0];cnt=struct.unpack('>i',p.r(4))[0]
ents=[]
for i in range(cnt):
    st=p.p;sp=p.entry();ents.append((raw[st:p.p],sp,st))
def name_of(i):
    e,sp,st=ents[i];t,a,b=sp['name'];return e[a-st+2:b-st].decode()
byname={}
for i in range(cnt):byname.setdefault(name_of(i),[]).append(i)
def states_raw(i):
    e,sp,st=ents[i];t,a,b=sp['states'];return e[a-st:b-st]
def ver_raw(i):
    e,sp,st=ents[i];t,a,b=sp['version'];return e[a-st:b-st]
def mk(i,bid,meta):
    return (b'\x08'+s('name')+s(name_of(i))+b'\x03'+s('version')+ver_raw(i)+b'\x0a'+s('states')+states_raw(i)+
            b'\x03'+s('id')+struct.pack('>i',bid)+b'\x02'+s('data')+struct.pack('>h',meta)+b'\x03'+s('runtimeId')+struct.pack('>i',i)+b'\x00')
# --- dat (current)
f=R+r'\runtime_block_states.dat'
d=gzip.open(f).read();assert d[0]==9 and d[3]==10
cur=struct.unpack('>i',d[4:8])[0]
add=b'';tot=0
ids={}
bid=start
for name,(cls,disp,base) in spec.items():
    ids[name]=bid
    for m,i in enumerate(byname['minecraft:'+name]):
        add+=mk(i,bid,m);tot+=1
    bid+=1
d=d[:4]+struct.pack('>i',cur+tot)+d[8:]+add
gzip.open(f,'wb').write(d)  # note gzip header irrelevant
# --- 729 palette + dat729
f=R+r'\block_palette_729.nbt'
d=gzip.open(f).read()
p=P(d);p.p=3+struct.unpack('>H',d[1:3])[0]
tt=p.r(1)[0];n=struct.unpack('>H',p.r(2))[0];p.r(n);lp=p.p;et=p.r(1)[0];cnt7=struct.unpack('>i',p.r(4))[0]
for _ in range(cnt7):p.val(10)
end=p.p
newp=b''
f2=R+r'\runtime_block_states_729.dat'
d2=gzip.open(f2).read();cur2=struct.unpack('>i',d2[4:8])[0]
add2=b'';rid=cnt7
for name in spec:
    for m,i in enumerate(byname['minecraft:'+name]):
        # palette entry: name, states, version
        newp+=b'\x08'+s('name')+s(name_of(i))+b'\x0a'+s('states')+states_raw(i)+b'\x03'+s('version')+ver_raw(i)+b'\x00'
        e=mk(i,ids[name],m); k=e.rfind(b'\x03'+s('runtimeId'))
        add2+=e[:k]+b'\x03'+s('runtimeId')+struct.pack('>i',rid)+b'\x00';rid+=1
d=d[:lp+1]+struct.pack('>i',cnt7+rid-cnt7)+d[lp+5:end]+newp+d[end:]
gzip.open(f,'wb').write(d)
d2=d2[:4]+struct.pack('>i',cur2+rid-cnt7)+d2[8:]+add2
gzip.open(f2,'wb').write(d2)
# --- java
def ins_after(file,pat,line):
    t=open(file,encoding='utf8').read();m=list(re.finditer(pat,t,re.M))[-1]
    t=t[:m.end()]+'\n'+line+t[m.end():];open(file,'w',encoding='utf8').write(t)
for name,(cls,disp,base) in spec.items():
    const=name.upper();b=ids[name]
    t=open(J+r'\BlockID.java',encoding='utf8').read().rstrip()
    assert t.endswith('}');t=t[:-1].rstrip()+f'\n    int {const} = {b};\n}}\n';open(J+r'\BlockID.java','w',encoding='utf8').write(t)
    ins_after(J+r'\Blocks.java',r'^        list\[\w+\] = \w+\.class; //\d+$',f'        list[{const}] = {cls}.class; //{b}')
    ins_after(J+r'\BlockTypes.java',r'^    public static final BlockType \w+ = register\(.*\);$',f'    public static final BlockType {const} = register("minecraft:{name}", BlockID.{const});')
    open(J+f'\{cls}.java','w',encoding='utf8').write(f'''package cn.nukkit.block;

public class {cls} extends {base} {{

    public {cls}() {{
        this(0);
    }}

    public {cls}(int meta) {{
        super(meta);
    }}

    @Override
    public int getId() {{
        return {const};
    }}

    @Override
    public String getName() {{
        return "{disp}";
    }}
}}
''')
json.dump(ids,open(sys.argv[1]+'.ids','w'))
print(ids,tot)
