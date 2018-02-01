

def func1(*arg, **kwargs):
	print(arg)
	print(type(arg))

	print('print(arg)')
	for i in arg:
		print(i)

	print(*arg)
	
	print(type(*arg))



	print(kwargs)
	foo = kwargs.get('foo')
	print(foo)
	# print(**kwargs)




if __name__ == '__main__':

	func1([1,2,3,4], foo=1, bar=2)
	