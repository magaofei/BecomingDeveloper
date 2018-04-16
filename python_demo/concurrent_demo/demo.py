import _thread
import threading
import multiprocessing

import concurrent.futures
MAX_WORKER = 10

def test(value=None):
    """
    测试函数
    """
    print(value)


class ThreadDemo(object):
    """
    Python3 名称为 _thread
    几乎没有用过
    功能：
    - 开线程 start_new_thread
    - 加锁 acquire  release   locked
    - 中断和退出 interrupt_main  exit

    """
    def run(self):
        _thread.start_new_thread(test, "foo")



class ThreadingDemo(object):
    """
    Threading 测试
    基于 _thread 
    - 获取激活数量
    - 获取运行线程数量
    - 开线程，运行，阻塞，等待，设置名字，获取名，后台执行
    - 信号量
    """

    def run(self):
        threads = []
        for i in range(MAX_WORKER):
            t = threading.Thread(target=test, kwargs=("foo"))
            threads.append(t)
            t.start()
        
        for t in threads:
            t.join()

class ConcurrentThreadingDemo(object):
    """
    基于 Threading 
    API 最简单，易于理解，容易使用
    - 线程池功能
    - 批量执行
    - 自定义最大线程数

    """

    def run(self):
        with concurrent.futures.ThreadPoolExecutor(MAX_WORKER) as exector:
            values = (i for i in range(10))
            results = exector.map(test, values)
            
            concurrent.futures.as_completed(results)



    



