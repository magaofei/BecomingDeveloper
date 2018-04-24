__doc__ = """
单例

线程之间安全
进程之间安全
多进程下开多线程，同进程下安全
"""
import concurrent.futures
class Singleton(object):
    _instances = {}
    def __call__(cls, *args, **kwargs):
        if cls not in cls._instances:
            cls._instances[cls] = super(Singleton, cls).__call__(*args, **kwargs)
        return cls._instances[cls]


def singleton(cls):
    instances = {}
    def getinstance(*args, **kwargs):
        if cls not in instances:
            instances[cls] = cls(*args, **kwargs)
        return instances[cls]
    return getinstance

try:
    # This is jython-specific
    from synchronize import make_synchronized
except ImportError:
    # This should work across different python implementations
    def make_synchronized(func):
        import threading
        func.__lock__ = threading.Lock()

        def synced_func(*args, **kws):
            with func.__lock__:
                return func(*args, **kws)

        return synced_func


@singleton
class Test(object):
    def __init__(self, i):
        pass
        # print(i)
        # print(id(self))


def test_singleton(i):
    print(id(Test(i)))

def open_threading():
    print("threading")
    with concurrent.futures.ThreadPoolExecutor(10) as future:
        tests = [future.submit(test_singleton, i) for i in range(10)]
        
if __name__ == '__main__':

    
    print("process")
    with concurrent.futures.ProcessPoolExecutor(10) as exector:
        tests_process = [exector.submit(open_threading) for i in range(10)]
        # for future in concurrent.futures.as_completed(tests):

            
            

        

        # future.as_complate()
        
        
