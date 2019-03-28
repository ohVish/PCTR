import multiprocessing


def funcion(id,pipe):
	upipe,_ =pipe
	upipe.send(id)
	upipe.close()


if __name__=='__main__':
	pipe = multiprocessing.Pipe(True)
	nCores =4#int(input('Numero de hilos:'))
	ids=[1,2,3,4]
	lista = []
	for i in range(nCores):
		proc = multiprocessing.Process(target=funcion,args=(i+1,pipe,))
		lista.append(proc)
		proc.start()
	for proc in lista:
		proc.join()
	try:
		while True:
			pepito = pipe[1].recv()
			print(format(pepito))
	except EOFError:
		pipe.close()

