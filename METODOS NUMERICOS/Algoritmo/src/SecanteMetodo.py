import numpy as np
import matplotlib.pyplot as plt
from sympy import symbols, lambdify
import pandas as pd

def secante_tabla(fx, xa, tolera, cifras):
    dx = 4*tolera
    xb = xa + dx
    tramo = dx
    tabla = []
    i = 0
    error_aproximado = 100  # Inicializamos con un valor mayor que la tolerancia
    while (error_aproximado >= tolera):
        fa = fx(xa)
        fb = fx(xb)
        xc = xa - fa*(xb-xa)/(fb-fa)
        tramo = abs(xc-xa)
        error_aproximado = abs((xc-xa)/xc)*100
        tabla.append([i, round(xc, cifras), fx(xc), round(error_aproximado, cifras)])
        xb = xa
        xa = xc
        i += 1
    tabla = np.array(tabla)
    return tabla

# PROGRAMA ---------------------
# INGRESO
x = symbols('x')
fx_simbolica = input("Por favor, ingrese la función que desea graficar: ")
fx = lambdify(x, fx_simbolica, "numpy")

a  = float(input("Por favor, ingrese el valor inicial a: "))
b  = float(input("Por favor, ingrese el valor inicial b: "))
xa = (a+b)/2
tolera = float(input("Por favor, ingrese la tolerancia: "))
cifras = int(input("Por favor, ingrese las cifras significativas: "))
tramos = 100

# PROCEDIMIENTO
tabla = secante_tabla(fx, xa, tolera, cifras)
n = len(tabla)
raiz = round(tabla[n-1,1], cifras)

# SALIDA
df = pd.DataFrame(tabla, columns=['Iteración', 'x', 'f(x)', 'ea'])
df['f(x)'] = df['f(x)'].apply(lambda x: "{:.2e}".format(x))
print(df)
print('raiz en: ', raiz)

# GRAFICA
xi = np.linspace(a, b, tramos+1)
fi = fx(xi)
plt.plot(xi, fi, label='f(x)')
plt.axhline(0, color='k')
plt.title('Método de la Secante')
plt.legend()
plt.grid()
plt.show()
