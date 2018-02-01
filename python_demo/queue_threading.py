"""
"""
from concurrent import futures
import requests
import time

# cc_list = ['www.baidu.com']

COUNT = 10
MAX_WORKERS = 8
BAIDU_URL = 'www.baidu.com'

cc_list = []
for i in range(COUNT):
    i = 'www.baidu.com'
    cc_list.append(i)


def download_one(cc):
    url = 'http://{}'.format(cc)
    resp = requests.get(url)
    return resp.text

def download_many():
    with futures.ThreadPoolExecutor(MAX_WORKERS) as executor:
        to_do = []
        for cc in sorted(cc_list):
            future = executor.submit(download_one, cc)
            to_do.append(future)
            msg = 'Scheduled for {}: {}'
            print(msg.format(cc, future))
        results = []
        for future in futures.as_completed(to_do):
            print('全部完成')
            res = future.result()[:10]
            msg = '{} result: {!r}'
            print(msg.format(future, res))
            results.append(res)

    return len(results)

def main():
    t0 = time.time()
    count = download_many()
    elapsed = time.time() - t0
    msg = '\n{} flags downloaded in {:.2f}s'
    print(msg.format(count, elapsed))


if __name__ == '__main__':
    main()





#!/usr/bin/env python
#coding:utf8
# import random,threading,time
# from queue import Queue
# #Producer thread
# class Producer(threading.Thread):
#     def __init__(self, t_name, queue):
#         threading.Thread.__init__(self,name=t_name)
#         self.data=queue
#     def run(self):
#         for i in range(10):    #随机产生10个数字 ，可以修改为任意大小
#             randomnum=random.randint(1,99)
#             print("%s: %s is producing %d to the queue!" % (time.ctime(), self.getName(), randomnum))
#             self.data.put(randomnum)  #将数据依次存入队列
#             time.sleep(1)
#         print("%s: %s finished!" %(time.ctime(), self.getName()))
 
# #Consumer thread
# class Consumer_even(threading.Thread):
#     def __init__(self,t_name,queue):
#         threading.Thread.__init__(self,name=t_name)
#         self.data=queue
#     def run(self):
#         while 1:
#             try:
#                 val_even = self.data.get(1,5)  #get(self, block=True, timeout=None) ,1就是阻塞等待,5是超时5秒
#                 if val_even%2==0:
#                     print("%s: %s is consuming. %d in the queue is consumed!" % (time.ctime(),self.getName(),val_even))
#                     time.sleep(2)
#                 else:
#                     self.data.put(val_even)
#                     time.sleep(2)
#             except:     #等待输入，超过5秒  就报异常
#                 print("%s: %s finished!" %(time.ctime(),self.getName()))
#                 break
# class Consumer_odd(threading.Thread):
#     def __init__(self,t_name,queue):
#         threading.Thread.__init__(self, name=t_name)
#         self.data=queue
#     def run(self):
#         while 1:
#             try:
#                 val_odd = self.data.get(1,5)
#                 if val_odd%2!=0:
#                     print("%s: %s is consuming. %d in the queue is consumed!" % (time.ctime(), self.getName(), val_odd))
#                     time.sleep(2)
#                 else:
#                     self.data.put(val_odd)
#                     time.sleep(2)
#             except:
#                 print("%s: %s finished!" % (time.ctime(), self.getName()))
#                 break
# #Main thread
# def main():
#     queue = Queue()
#     producer = Producer('Pro.', queue)
#     consumer_even = Consumer_even('Con_even.', queue)
#     consumer_odd = Consumer_odd('Con_odd.',queue)
#     producer.start()
#     consumer_even.start()
#     consumer_odd.start()
#     producer.join()
#     consumer_even.join()
#     consumer_odd.join()
#     print('All threads terminate!')
 
# if __name__ == '__main__':
#     main()
