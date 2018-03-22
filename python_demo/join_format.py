import cProfile

LENG = 1000000


def test_plus():
    a = ''
    for i in range(LENG):
        a += str(i)
    # print(a)
    return a

def test_join():
    l = [str(i) for i in range(LENG)]
    a = 'a'.join(l)
    return a
        

if __name__ == '__main__':
    cProfile.run("test_plus()")
    cProfile.run("test_join()")
    # test_plus()

