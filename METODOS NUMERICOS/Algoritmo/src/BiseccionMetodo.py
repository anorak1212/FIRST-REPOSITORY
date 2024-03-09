import sympy as sp
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

def metodo_biseccion(func, a, b, tol, cifras):
    if func.subs(x, a)*func.subs(x, b) >= 0:
        print("No se puede aplicar el método de la bisección en el intervalo dado.")
        return
    c = a
    iteracion = 0
    data = {'Iteración': [], 'a': [], 'b': [], 'x': [], 'f(a)': [], 'f(b)': [], 'f(x)': [], 'Error (%)': []}
    while ((b-a) >= tol):
        c_anterior = c
        c = (a+b)/2
        error = abs((c - c_anterior) / c) * 100
        data['Iteración'].append(iteracion)
        data['a'].append(round(a, cifras))
        data['b'].append(round(b, cifras))
        data['x'].append(round(c, cifras))
        data['f(a)'].append(round(func.subs(x, a), cifras))
        data['f(b)'].append(round(func.subs(x, b), cifras))
        data['f(x)'].append(round(func.subs(x, c), cifras))
        data['Error (%)'].append(round(error, cifras))
        if error < tol:
            print("La tolerancia se ha alcanzado en la iteración", iteracion)
            break
        if func.subs(x, c) == 0.0:
            break
        if func.subs(x, c)*func.subs(x, a) < 0:
            b = c
        else:
            a = c
        iteracion += 1
    df = pd.DataFrame(data)
    print(df)
    return c

x = sp.symbols('x')
f = sp.sympify(input("Introduce la función: ").replace('e', 'exp(1)'))
a = float(input("Introduce el límite inferior del intervalo: "))
b = float(input("Introduce el límite superior del intervalo: "))
tol = float(input("Introduce la tolerancia: "))
cifras = int(input("Introduce la cantidad de cifras significativas: "))

print("La raíz es: ", round(metodo_biseccion(f, a, b, tol, cifras), cifras))

# Graficación de la función
f_lambdified = sp.lambdify(x, f, "numpy")
x_vals = np.linspace(a, b, 400)
y_vals = f_lambdified(x_vals)

plt.figure(figsize=(8, 6))
plt.plot(x_vals, y_vals, label=str(f))
plt.title('Gráfica de la función')
plt.xlabel('x')
plt.ylabel('f(x)')
plt.axhline(0, color='black',linewidth=0.6)
plt.axvline(0, color='black',linewidth=0.6)
plt.grid(color = 'gray', linestyle = '--', linewidth = 0.5)
plt.legend()
plt.show()
