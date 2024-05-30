[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/XbZw8B6J)
# Bishop Exchange


Egy 5 sorból és 4 oszlopból álló sakktábla legfelső sorába 4 sötét, legalsó
sorába 4 világos futót helyezünk. Cseréljük meg a figurákat, olyan módon,
hogy azok futólépésben léphetnek, továbbá nem léphetnek olyan mezőre,
amelyet ellentétes színű figura üt.

## Egy megoldási lehetőség:
(1 = fehér; 2 = fekete)

2 2 2 2 <br>
0 0 0 0 <br>
0 0 0 0 <br>
0 0 0 0 <br>
1 1 1 1 <br>

TwoPhaseMove[from=(4,2), to=(3,1)] <br> 2 2 2 2 <br>
0 0 0 0 <br>
0 0 0 0 <br>
0 1 0 0 <br>
1 1 0 1 <br>

TwoPhaseMove[from=(0,1), to=(1,2)]  <br>2 0 2 2 <br>
0 0 2 0 <br>
0 0 0 0 <br>
0 1 0 0 <br>
1 1 0 1 <br>

TwoPhaseMove[from=(4,3), to=(1,0)] <br> 2 0 2 2 <br>
1 0 2 0 <br>
0 0 0 0 <br>
0 1 0 0 <br>
1 1 0 0 <br>

TwoPhaseMove[from=(0,0), to=(3,3)] <br> 0 0 2 2 <br>
1 0 2 0 <br>
0 0 0 0 <br>
0 1 0 2 <br>
1 1 0 0 <br>

TwoPhaseMove[from=(4,1), to=(3,2)]  <br>0 0 2 2 <br>
1 0 2 0 <br>
0 0 0 0 <br>
0 1 1 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(1,2), to=(3,0)]  <br>0 0 2 2 <br>
1 0 0 0 <br>
0 0 0 0 <br>
2 1 1 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(1,0), to=(0,1)]  <br>0 1 2 2 <br>
0 0 0 0 <br>
0 0 0 0 <br>
2 1 1 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(0,2), to=(1,1)] <br> 0 1 0 2 <br>
0 2 0 0 <br>
0 0 0 0 <br>
2 1 1 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(3,2), to=(2,3)]  <br>0 1 0 2 <br>
0 2 0 0 <br>
0 0 0 1 <br>
2 1 0 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(3,0), to=(2,1)] <br> 0 1 0 2 <br>
0 2 0 0 <br>
0 2 0 1 <br>
0 1 0 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(3,1), to=(1,3)] <br> 0 1 0 2 <br>
0 2 0 1 <br>
0 2 0 1 <br>
0 0 0 2 <br>
1 0 0 0 <br>

TwoPhaseMove[from=(2,1), to=(4,3)] <br> 0 1 0 2 <br>
0 2 0 1 <br>
0 0 0 1 <br>
0 0 0 2 <br>
1 0 0 2 <br>

TwoPhaseMove[from=(2,3), to=(4,1)] <br> 0 1 0 2 <br>
0 2 0 1 <br>
0 0 0 0 <br>
0 0 0 2 <br>
1 1 0 2 <br>

TwoPhaseMove[from=(0,3), to=(2,1)]  <br>0 1 0 0 <br>
0 2 0 1 <br>
0 2 0 0 <br>
0 0 0 2 <br>
1 1 0 2 <br>

TwoPhaseMove[from=(0,1), to=(2,3)]  <br>0 0 0 0 <br>
0 2 0 1 <br>
0 2 0 1 <br>
0 0 0 2 <br>
1 1 0 2 <br>

TwoPhaseMove[from=(2,1), to=(1,0)] <br> 0 0 0 0 <br>
2 2 0 1 <br>
0 0 0 1 <br>
0 0 0 2 <br>
1 1 0 2 <br>

TwoPhaseMove[from=(2,3), to=(1,2)] <br> 0 0 0 0 <br>
2 2 1 1 <br>
0 0 0 0 <br>
0 0 0 2 <br>
1 1 0 2 <br>

TwoPhaseMove[from=(1,1), to=(2,0)] <br> 0 0 0 0 <br>
2 0 1 1 <br>
2 0 0 0 <br>
0 0 0 2 <br>
1 1 0 2 <br>

TwoPhaseMove[from=(4,1), to=(3,0)] <br> 0 0 0 0 <br>
2 0 1 1 <br>
2 0 0 0 <br>
1 0 0 2 <br>
1 0 0 2 <br>

TwoPhaseMove[from=(3,3), to=(4,2)]  <br>0 0 0 0 <br>
2 0 1 1 <br>
2 0 0 0 <br>
1 0 0 0 <br>
1 0 2 2 <br>

TwoPhaseMove[from=(1,3), to=(2,2)]  <br>0 0 0 0 <br>
2 0 1 0 <br>
2 0 1 0 <br>
1 0 0 0 <br>
1 0 2 2 <br>

TwoPhaseMove[from=(1,0), to=(3,2)] <br> 0 0 0 0 <br>
0 0 1 0 <br>
2 0 1 0 <br>
1 0 2 0 <br>
1 0 2 2 <br>

TwoPhaseMove[from=(2,2), to=(0,0)]  <br>1 0 0 0 <br>
0 0 1 0 <br>
2 0 0 0 <br>
1 0 2 0 <br>
1 0 2 2 <br>

TwoPhaseMove[from=(2,0), to=(0,2)]  <br>1 0 2 0 <br>
0 0 1 0 <br>
0 0 0 0 <br>
1 0 2 0 <br>
1 0 2 2 <br>

TwoPhaseMove[from=(4,0), to=(2,2)] <br> 1 0 2 0 <br>
0 0 1 0 <br>
0 0 1 0 <br>
1 0 2 0 <br>
0 0 2 2 <br>

TwoPhaseMove[from=(4,2), to=(2,0)] <br> 1 0 2 0 <br>
0 0 1 0 <br>
2 0 1 0 <br>
1 0 2 0 <br>
0 0 0 2 <br>

TwoPhaseMove[from=(2,2), to=(3,3)]  <br>1 0 2 0 <br>
0 0 1 0 <br>
2 0 0 0 <br>
1 0 2 1 <br>
0 0 0 2 <br>

TwoPhaseMove[from=(0,2), to=(1,3)]  <br>1 0 0 0 <br>
0 0 1 2 <br>
2 0 0 0 <br>
1 0 2 1 <br>
0 0 0 2 <br>

TwoPhaseMove[from=(1,2), to=(0,1)] <br> 1 1 0 0 <br>
0 0 0 2 <br>
2 0 0 0 <br>
1 0 2 1 <br>
0 0 0 2 <br>

TwoPhaseMove[from=(2,0), to=(3,1)] <br> 1 1 0 0 <br>
0 0 0 2 <br>
0 0 0 0 <br>
1 2 2 1 <br>
0 0 0 2 <br>

TwoPhaseMove[from=(3,3), to=(1,1)] <br> 1 1 0 0 <br>
0 1 0 2 <br>
0 0 0 0 <br>
1 2 2 0 <br>
0 0 0 2 <br>

TwoPhaseMove[from=(3,1), to=(4,2)]  <br>1 1 0 0 <br>
0 1 0 2 <br>
0 0 0 0 <br>
1 0 2 0 <br>
0 0 2 2 <br>

TwoPhaseMove[from=(3,0), to=(0,3)] <br> 1 1 0 1 <br>
0 1 0 2 <br>
0 0 0 0 <br>
0 0 2 0 <br>
0 0 2 2 <br>

TwoPhaseMove[from=(1,3), to=(4,0)] <br> 1 1 0 1 <br>
0 1 0 0 <br>
0 0 0 0 <br>
0 0 2 0 <br>
2 0 2 2 <br>

TwoPhaseMove[from=(1,1), to=(0,2)]  <br>1 1 1 1 <br>
0 0 0 0 <br>
0 0 0 0 <br>
0 0 2 0 <br>
2 0 2 2 <br>

TwoPhaseMove[from=(3,2), to=(4,1)] <br> 1 1 1 1 <br>
0 0 0 0 <br>
0 0 0 0 <br>
0 0 0 0 <br>
2 2 2 2 <br>




