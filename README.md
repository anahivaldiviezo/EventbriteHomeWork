# EventbriteHomeWork
Eventbrite Homework - Adivinador de numeros

A este ejercicio lo separe en 2

Pestaña 1 (persona adivinador - maquina pensador)

La maquina elige un numero random, cuyos 4 dígitos no se repiten, que el usuario deberá adivinar, la maquina comparara los números ingresados del usuario con el que eligió, devolviendo los diferentes resultados de correctos y regulares, hasta que el usuario de con el numero correcto.

Pestaña 2 (persona pensador - maquina adivinador)

Divido el problema en 2  pasos, primero encuentro los números que están contenidos en la solución no necesariamente el correcto, puede ser un numero con resultados regulares + correctos , o solo regulares  teniendo en cuenta que la cantidad de aciertos es la cantidad de correctos + cantidad de regulares , y luego los permuto hasta obtener la solución correcta.

El algoritmo para encontrar una lista con 4 aciertos , se basa en ir subiendo el puntaje de aciertos (regulares + correctos), si el puntaje se mantiene, se sigue probando otro numero en el mismo Indice, en caso de que el puntaje baje, se revierte el numero (vuelve a ser el anterior), se pasa a la siguiente posición con el siguiente numero, si el puntaje sube, se pasa al siguiente indice con el siguiente numero.
Para todos los casos mi numero inicial es 0123 , indice inicial 0 y numero inicial a evaluar el 4. El valor del indice se mantiene entre 0 y 3. El numero de 0 a 9 y no puede ser un valor repetido dentro de los 4 dígitos.

Evaluo mi numero inicial 0123 contra el numero del pensador , que me da como resultado   la cantidad de regulares + correctos.

El numero que ingresa el usuario es 5723

[0]123 | 2 (2 correctos + 0 regulares)

[4]123 | 2

[5]123 | 3

5[6]23 | 3

5[7]23 | 4 -> en este caso llego al resultado correcto sin permutaciones.


Otro ejemplo 3954

[0]123 | 1 Regular 0 correctos

[4]123 | 2

4[5]23 | 3

45[6]3 | 3

45[7]3 | 3

45[8]3 | 3

45[9]3 | 4 Regular 0 correctos

Numero con 4 aciertos : 4593 —> necesita permutarse, hasta coincidir con el correcto.

En el segundo paso tomo este numero 4593 y realizo permutaciones hasta dar con el correcto
Para este algoritmo hice uso del método Collections.swap para intercambiar 2 números dentro de una lista.
Collections.swap(lista,primerElemento,segundoElemento)
Realizo las permutaciones de la siguiente manera swap[0,1] , swap[2,3] , swap[1,2] 
En el caso de swap[0,1] , swap[2,3] si la cantidad de correctos disminuye, se revierte para siempre intentar dejar el numero un paso mas cerca de la solucion.

La permutación swap[1,2] es obligatoria sin importar si disminuye la cantidad de correctos ya que garantiza que los números se mezclen.

4593 | 0 correctos

[54]93 | 0

54[39] | 0

5[34]9 | 0

[35]49 | 1

35[94] | 2

3[95]4 | 4  -> resultado correcto 


Links a cobertura de tests
https://github.com/anahivaldiviezo/EventbriteHomeWork/blob/master/app/src/test/java/com/valdiviezo/anahi/eventbritehomework/HomeWorkUtilsTests.java
https://github.com/anahivaldiviezo/EventbriteHomeWork/blob/master/app/src/test/java/com/valdiviezo/anahi/eventbritehomework/IANumeroAdivinadorTests.java

Modelo
https://github.com/anahivaldiviezo/EventbriteHomeWork/tree/master/app/src/main/java/com/valdiviezo/anahi/eventbritehomework/model

Utils
Aqui se encuentra toda la logica/metodos para los ejercicios
https://github.com/anahivaldiviezo/EventbriteHomeWork/blob/master/app/src/main/java/com/valdiviezo/anahi/eventbritehomework/Utils/IANumeroAdivinador.java

https://github.com/anahivaldiviezo/EventbriteHomeWork/blob/master/app/src/main/java/com/valdiviezo/anahi/eventbritehomework/Utils/HomeWorkUtils.java

Layouts de las vistas 
https://github.com/anahivaldiviezo/EventbriteHomeWork/tree/master/app/src/main/res/layout
Manejo de las vistas, eventos.
https://github.com/anahivaldiviezo/EventbriteHomeWork/tree/master/app/src/main/java/com/valdiviezo/anahi/eventbritehomework


