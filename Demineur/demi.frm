VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   3  'Fixed Dialog
   Caption         =   "Démi"
   ClientHeight    =   8820
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   9630
   KeyPreview      =   -1  'True
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   8820
   ScaleWidth      =   9630
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'CenterScreen
   Begin VB.ComboBox Combo1 
      Height          =   315
      ItemData        =   "demi.frx":0000
      Left            =   8760
      List            =   "demi.frx":0016
      Style           =   2  'Dropdown List
      TabIndex        =   1
      Top             =   7880
      Width           =   735
   End
   Begin VB.CommandButton Command2 
      Caption         =   "Nouvelle partie"
      Height          =   495
      Left            =   4080
      Style           =   1  'Graphical
      TabIndex        =   0
      Top             =   8160
      Width           =   1455
   End
   Begin VB.Label kase 
      BorderStyle     =   1  'Fixed Single
      Height          =   255
      Index           =   0
      Left            =   240
      TabIndex        =   5
      Top             =   720
      Visible         =   0   'False
      Width           =   255
   End
   Begin VB.Label Label3 
      Alignment       =   2  'Center
      BackColor       =   &H8000000E&
      Caption         =   $"demi.frx":0035
      ForeColor       =   &H00FF0000&
      Height          =   1455
      Left            =   480
      TabIndex        =   4
      Top             =   6960
      Width           =   2055
   End
   Begin VB.Label Label2 
      BackColor       =   &H8000000E&
      Height          =   1935
      Left            =   240
      TabIndex        =   3
      Top             =   6720
      Width           =   2535
   End
   Begin VB.Label Label1 
      Alignment       =   2  'Center
      Caption         =   "Nombre de mines"
      Height          =   255
      Left            =   7200
      TabIndex        =   2
      Top             =   7920
      Width           =   1575
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit
Dim abscisse As Integer
Dim ordonnée As Integer
Dim tostop As Boolean
Const couleurnormale = &H8000000F 'gris
Const couleurselec = &H80FF80 'vert pale
Const couleurtrankil = &HFFFFC0   'bleu pale
Private Declare Function GetAsyncKeyState Lib "user32" (ByVal vKey As Long) As Integer

Private Sub Command2_Click()
Dim k As Integer
Dim l As Integer
Dim c As Integer
Dim d As Integer
Dim e As Integer
Dim ff As Integer

Command2.Enabled = False
Combo1.Enabled = False

e = GetAsyncKeyState(27)
e = GetAsyncKeyState(37)
e = GetAsyncKeyState(38)
e = GetAsyncKeyState(39)
e = GetAsyncKeyState(40)

Randomize Timer

c = 1

For l = 1 To 20
For k = 1 To 20
Load kase(c)
kase(c).Left = 1875 + 250 * k
kase(c).Top = 1000 + 250 * l
kase(c).Visible = True
c = c + 1
Next k
Next l

d = CInt(Combo1.Text)

For k = 1 To d
p:
l = Int(20 * Rnd) + 1
c = Int(20 * Rnd) + 1
    
    If (l = 1 And c = 1) Or (l = 20 And c = 20) Then GoTo p
    
    If kase(f(l, c)).Tag <> "bomb" Then
    kase(f(l, c)).Tag = "bomb"
    Else
    GoTo p
    End If
    
Next k

DoEvents

For k = 1 To 20
For l = 1 To 20
kase(f(k, l)).Tag = Compte(k, l)
Next l
Next k

abscisse = 1
ordonnée = 1
tostop = False

kase(1).Caption = kase(1).Tag
kase(1).BackColor = couleurselec

Call theboucle

End Sub

Private Sub theboucle()
Dim k As Integer

Do

recevoirtouches
If tostop Then Exit Do

DoEvents

Loop

For k = 1 To 400
Unload kase(k)
Next k

Command2.Enabled = True
Combo1.Enabled = True

End Sub

Private Function f(ByVal a As Integer, ByVal b As Integer) As Integer
f = 20 * (a - 1) + b
End Function

Private Sub recevoirtouches()
Static a(1 To 5) As Boolean
Static k As Byte

If GetAsyncKeyState(27) <> 0 Then a(1) = True 'Echap
If GetAsyncKeyState(38) <> 0 Then a(2) = True 'fleche haut
If GetAsyncKeyState(40) <> 0 Then a(3) = True 'fleche bas
If GetAsyncKeyState(39) <> 0 Then a(4) = True 'fleche droite
If GetAsyncKeyState(37) <> 0 Then a(5) = True 'fleche gauche


If a(1) Then 'touche Echap
tostop = True
End If

If a(2) Then 'fleche haut
Call todo(vbKeyUp)
End If

If a(3) Then 'fleche bas
Call todo(vbKeyDown)
End If

If a(4) Then 'fleche droite
Call todo(vbKeyRight)
End If

If a(5) Then 'fleche gauche
Call todo(vbKeyLeft)
End If

For k = 1 To 5
a(k) = False
Next k

End Sub

Private Sub todo(KeyAsci As Integer)
Dim ok As Boolean
Dim lastord As Integer
Dim lastabs As Integer
Dim k As Currency

lastord = ordonnée
lastabs = abscisse
ok = False

Select Case KeyAsci

Case vbKeyUp

If ordonnée > 1 Then
ok = True
ordonnée = ordonnée - 1
End If

Case vbKeyDown

If ordonnée < 20 Then
ordonnée = ordonnée + 1
ok = True
End If

Case vbKeyRight

If abscisse < 20 Then
ok = True
abscisse = abscisse + 1
End If

Case vbKeyLeft

If abscisse > 1 Then
ok = True
abscisse = abscisse - 1
End If

End Select


If ok Then
If kase(f(ordonnée, abscisse)).Tag = "bomb" Or (ordonnée = 20 And abscisse = 20) Then
    
If Not ((ordonnée = 20 And abscisse = 20)) Then
MsgBox "Perdu ! Retentez votre chance !", vbOKOnly + vbCritical, "Démi :"
Else
MsgBox "Congratulations !", vbOKOnly + vbExclamation, "Démi :"
End If

    For k = 1 To 400
    If kase(k).Tag = "bomb" Then
        kase(k).BackColor = vbRed
    End If
    Next k
    DoEvents
    
    k = Timer
    Do
    Loop Until (Timer - k > 5)
    tostop = True
    Exit Sub
    
    
Else
kase(f(ordonnée, abscisse)).Caption = kase(f(ordonnée, abscisse)).Tag
kase(f(lastord, lastabs)).BackColor = couleurnormale
kase(f(ordonnée, abscisse)).BackColor = couleurselec
If kase(f(lastord, lastabs)).Caption = "0" Then kase(f(lastord, lastabs)).BackColor = couleurtrankil
End If

DoEvents
k = Timer
Do
Loop Until (Timer - k) > 0.3

End If

End Sub

Private Function Compte(ByVal k As Integer, ByVal l As Integer) As String
Dim c As Integer
Dim d As Integer
Dim e As Integer
Dim ff As Integer
Dim g As String

    c = 0
        
        For d = -1 To 1 Step 2
        For e = -1 To 1
        ff = f(k + d, l + e)
        If (k + d < 1) Or (k + d > 20) Or (l + e < 1 Or l + e > 20) Then GoTo f
        g = kase(ff).Tag
        If g = "bomb" Then c = c + 1
f:
        Next e
        Next d

        ff = f(k, l - 1)
        If (l = 1) Then GoTo g
        g = kase(ff).Tag
        If g = "bomb" Then c = c + 1
g:
        ff = f(k, l + 1)
        If l = 20 Then GoTo h
        g = kase(ff).Tag
        If g = "bomb" Then c = c + 1
h:

If kase(f(k, l)).Tag <> "bomb" Then
Compte = CStr(c)
Else
Compte = "bomb"
End If

End Function

Private Sub Form_Load()
Combo1.ListIndex = 2
End Sub

Private Sub Form_QueryUnload(Cancel As Integer, UnloadMode As Integer)
Dim but As Integer
but = MsgBox("Tu veux vraiment faire quelque chose de plus nul que ce jeu ?", vbYesNo + vbQuestion, "Sale battard !")

If but = vbYes Then
MsgBox "Démi a été fait par Thomas Jannaud", vbOKOnly + vbInformation, "Démi :"
End
Exit Sub
End If

MsgBox "C'est bien fiston.", vbInformation + vbOKOnly, "Démi :"

End Sub
