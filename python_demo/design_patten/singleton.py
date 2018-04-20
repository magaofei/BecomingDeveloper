import concurrent.futures
class Singleton(object):
    _instances = {}
    def __call__(cls, *args, **kwargs):
        if cls not in cls._instances:
            cls._instances[cls] = super(Singleton, cls).__call__(*args, **kwargs)
        return cls._instances[cls]


def singleton(class_):
    instances = {}
    def getinstance(*args, **kwargs):
        if class_ not in instances:
            instances[class_] = class_(*args, **kwargs)
        return instances[class_]
    return getinstance


@singleton
class Test(object):
    def __init__(self):
        print(id(self))


def test_singleton():
    for i in range(10):
        t = Test()

if __name__ == '__main__':

    with concurrent.futures.ThreadPoolExecutor(10) as future:
        f = future.submit(test_singleton)
        print(f.result())

        # future.as_complate()
        
        
