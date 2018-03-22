
class Lam(object):

    def lam(self, x):
        return x * 2

    

if __name__ == '__main__':
    x = 1
    l = Lam()
    result = l.lam(x)
    print(result)

    z = 1
    # lambda 是一个匿名函数
    y = lambda z: z * 2
    print(y(z))


    d = map( lambda x: x*x, [y for y in range(10)] )
    print(d)


            