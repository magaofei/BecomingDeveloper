import time

"""
time.perf_counter  高性能计数

"""

def clock(func):
	print(__name__)
	def clocked(*args):

		t0 = time.perf_counter()
		result = func(*args)
		elapsed = time.perf_counter() - t0
		name = func.__name__
		arg_str = ', '.join(repr(arg) for arg in args)
		print('[%0.8fs] %s(%s) -> %r' % (elapsed, name, arg_str, result))
		return result

	return clocked