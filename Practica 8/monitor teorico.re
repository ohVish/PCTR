//Monitor teorico de Hoare
//Politica de señalizacion -> Señalar y continuar

Monitor
Integer:anguilas;
Condition:vacio;
Condition:cocinero:

procedure comer
begin
	while anguilas==0
		send(cocinero);
		wait(vacio);
	anguilas--;
end

procedure rellenar
begin
	while anguilas>0
		wait(cocinero);
	anguilas=m;
	m*send(vacio);
end


vikingo
begin
	loop
	comer();
	end
end

cocinero
begin
	loop
	reponer();
	end
end