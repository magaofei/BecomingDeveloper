__doc__ = """
iter for a number demo
"""

class IterNumber(object):
    """
    """
    def __init__(self, num):
        self.num = num
        self.index = 1
    
    def __iter__(self):
        return self
    
    def __next__(self):
        
        r = int(self.num / self.index % 10)
        if r <= 0:
            raise StopIteration
        self.index *= 10
        return r

def y_number(number):
    index = 1
    r = 1
    while r > 0:
        r = int(number / index % 10)
        index *= 10
        yield r
    


if __name__ == '__main__':
    # i = IterNumber(123)
    # for a in i:
    #     print(a)
    # r = next(i)
    # print(r)
    # r = next(i)
    # print(r)
    # r = next(i)
    # print(r)
    # r = next(i)
    # print(r)
    r = y_number(123)
    for i in r:
        print(i)

