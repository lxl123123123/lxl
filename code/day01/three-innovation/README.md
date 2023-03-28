下面程序运行结果是 ()
#define FUNC(x,y)(x>y)?'a':'b'
int main(){
    unsigned int a = 10;
    char b = 1;
    unsigned char c = -1;
    printf("%c,%c\n",FUNC(a,b),FUNC(a,c));
    return 0;
}
A: a,a
B: a,b
C: b,a
D: a,c
E: b,b