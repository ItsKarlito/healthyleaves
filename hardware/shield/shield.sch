EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
Text GLabel 1075 7075 0    50   Input ~ 0
GND
Text GLabel 1075 6975 0    50   Input ~ 0
VUSB-L
Text GLabel 1075 6875 0    50   Input ~ 0
VBAT-L
Text GLabel 1075 6775 0    50   Input ~ 0
GND
Text GLabel 1075 6675 0    50   Input ~ 0
3V3-L
Text GLabel 1075 6575 0    50   Input ~ 0
RST
Text GLabel 1075 6475 0    50   Input ~ 0
13
Text GLabel 1075 6375 0    50   Input ~ 0
12
Text GLabel 1075 6275 0    50   Input ~ 0
14
Text GLabel 1075 6175 0    50   Input ~ 0
27
Text GLabel 1075 6075 0    50   Input ~ 0
26
Text GLabel 1075 5975 0    50   Input ~ 0
25
Text GLabel 1075 5875 0    50   Input ~ 0
35
Text GLabel 1075 5775 0    50   Input ~ 0
34
Text GLabel 1075 5675 0    50   Input ~ 0
33
Text GLabel 1075 5575 0    50   Input ~ 0
32
Text GLabel 1075 5475 0    50   Input ~ 0
39
Text GLabel 1075 5375 0    50   Input ~ 0
38
Text GLabel 1075 5275 0    50   Input ~ 0
37
Text GLabel 1075 5175 0    50   Input ~ 0
36
$Comp
L Connector_Generic:Conn_01x20 J1
U 1 1 5FA5D3D6
P 1275 6075
F 0 "J1" H 1225 7225 50  0000 L CNN
F 1 "Conn_01x20" H 875 7125 50  0000 L CNN
F 2 "Connector_PinHeader_2.54mm:PinHeader_1x20_P2.54mm_Vertical" H 1275 6075 50  0001 C CNN
F 3 "~" H 1275 6075 50  0001 C CNN
	1    1275 6075
	1    0    0    -1  
$EndComp
Text GLabel 1675 5375 2    50   Input ~ 0
TX
Text GLabel 1675 5275 2    50   Input ~ 0
21
Text GLabel 1675 5175 2    50   Input ~ 0
GND
$Comp
L Connector_Generic:Conn_01x20 J2
U 1 1 5FA59AFD
P 1475 6075
F 0 "J2" H 1475 7225 50  0000 C CNN
F 1 "Conn_01x20" H 1300 7125 50  0000 C CNN
F 2 "Connector_PinHeader_2.54mm:PinHeader_1x20_P2.54mm_Vertical" H 1475 6075 50  0001 C CNN
F 3 "~" H 1475 6075 50  0001 C CNN
	1    1475 6075
	-1   0    0    -1  
$EndComp
Text GLabel 1675 5475 2    50   Input ~ 0
RX
Text GLabel 1675 5575 2    50   Input ~ 0
22
Text GLabel 1675 5675 2    50   Input ~ 0
19
Text GLabel 1675 5775 2    50   Input ~ 0
23
Text GLabel 1675 5875 2    50   Input ~ 0
18
Text GLabel 1675 5975 2    50   Input ~ 0
5
Text GLabel 1675 6075 2    50   Input ~ 0
15
Text GLabel 1675 6175 2    50   Input ~ 0
2
Text GLabel 1675 6275 2    50   Input ~ 0
0
Text GLabel 1675 6375 2    50   Input ~ 0
4
Text GLabel 1675 6475 2    50   Input ~ 0
17
Text GLabel 1675 6575 2    50   Input ~ 0
16
Text GLabel 1675 6675 2    50   Input ~ 0
3V3-R
Text GLabel 1675 6775 2    50   Input ~ 0
GND
Text GLabel 1675 6875 2    50   Input ~ 0
VBAT-R
Text GLabel 1675 6975 2    50   Input ~ 0
VUSB-R
Text GLabel 1675 7075 2    50   Input ~ 0
GND
$Comp
L Device:R R1
U 1 1 5FA934B4
P 1175 1450
F 0 "R1" H 1105 1404 50  0000 R CNN
F 1 "LDR" H 1105 1495 50  0000 R CNN
F 2 "OptoDevice:R_LDR_4.9x4.2mm_P2.54mm_Vertical" V 1105 1450 50  0001 C CNN
F 3 "~" H 1175 1450 50  0001 C CNN
	1    1175 1450
	-1   0    0    1   
$EndComp
$Comp
L Device:R R2
U 1 1 5FA96C57
P 1175 1750
F 0 "R2" H 1105 1704 50  0000 R CNN
F 1 "100k" H 1105 1795 50  0000 R CNN
F 2 "Resistor_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 1105 1750 50  0001 C CNN
F 3 "~" H 1175 1750 50  0001 C CNN
	1    1175 1750
	-1   0    0    1   
$EndComp
Text GLabel 1350 1600 2    50   Input ~ 0
36
Text GLabel 1375 7400 1    50   Input ~ 0
GND
$Comp
L power:GND #PWR03
U 1 1 5FAA9346
P 1375 7400
F 0 "#PWR03" H 1375 7150 50  0001 C CNN
F 1 "GND" H 1380 7227 50  0000 C CNN
F 2 "" H 1375 7400 50  0001 C CNN
F 3 "" H 1375 7400 50  0001 C CNN
	1    1375 7400
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR01
U 1 1 5FAAED8F
P 1175 1900
F 0 "#PWR01" H 1175 1650 50  0001 C CNN
F 1 "GND" H 1180 1727 50  0000 C CNN
F 2 "" H 1175 1900 50  0001 C CNN
F 3 "" H 1175 1900 50  0001 C CNN
	1    1175 1900
	1    0    0    -1  
$EndComp
Text GLabel 1175 1300 1    50   Input ~ 0
3V3-L
Wire Wire Line
	1350 1600 1175 1600
Connection ~ 1175 1600
Text Notes 750  825  0    100  ~ 0
Light Sensor
$Comp
L power:GND #PWR02
U 1 1 5FAD2823
P 4575 1900
F 0 "#PWR02" H 4575 1650 50  0001 C CNN
F 1 "GND" H 4580 1727 50  0000 C CNN
F 2 "" H 4575 1900 50  0001 C CNN
F 3 "" H 4575 1900 50  0001 C CNN
	1    4575 1900
	1    0    0    -1  
$EndComp
Text GLabel 4575 1300 1    50   Input ~ 0
3V3-R
Text GLabel 4975 1600 2    50   Input ~ 0
38
Text Notes 3825 825  0    100  ~ 0
Temperature Sensor
$Comp
L Sensor_Temperature:LM35-LP U1
U 1 1 5FB449D9
P 4575 1600
F 0 "U1" H 4245 1646 50  0000 R CNN
F 1 "TMP36" H 4245 1555 50  0000 R CNN
F 2 "Package_TO_SOT_THT:TO-92L_Inline" H 4625 1350 50  0001 L CNN
F 3 "http://www.ti.com/lit/ds/symlink/lm35.pdf" H 4575 1600 50  0001 C CNN
	1    4575 1600
	1    0    0    -1  
$EndComp
Text GLabel 2650 1425 0    50   Input ~ 0
3V3-R
$Comp
L power:GND #PWR04
U 1 1 5FB30986
P 2650 1525
F 0 "#PWR04" H 2650 1275 50  0001 C CNN
F 1 "GND" H 2650 1350 50  0000 C CNN
F 2 "" H 2650 1525 50  0001 C CNN
F 3 "" H 2650 1525 50  0001 C CNN
	1    2650 1525
	1    0    0    -1  
$EndComp
Text GLabel 2650 1325 0    50   Input ~ 0
37
Text Notes 2175 825  0    100  ~ 0
Moisture Sensor
$Comp
L Connector_Generic:Conn_01x03 J3
U 1 1 5FB2A1DF
P 2850 1425
F 0 "J3" H 2850 1725 50  0000 C CNN
F 1 "SEN0193" H 2850 1650 50  0000 C CNN
F 2 "Connector_PinHeader_2.54mm:PinHeader_1x03_P2.54mm_Vertical" H 2850 1425 50  0001 C CNN
F 3 "~" H 2850 1425 50  0001 C CNN
	1    2850 1425
	1    0    0    -1  
$EndComp
$EndSCHEMATC
