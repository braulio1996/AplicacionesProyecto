import 'package:appdis/src/models/user_model.dart';
import 'package:appdis/src/services/user_services.dart';
import 'package:flutter/material.dart';
import 'package:appdis/src/pages/utils.dart' as utils;

class TransferPage extends StatefulWidget {
  const TransferPage({Key key}) : super(key: key);

  @override
  _TransferPageState createState() => _TransferPageState();
}

class _TransferPageState extends State<TransferPage> {
  final user = UserModel();
  final formkey = GlobalKey<FormState>();
  final formkey2 = GlobalKey<FormState>();
  final scaffoldkey = GlobalKey<ScaffoldState>();
  String tipoCuenta = '';
  String institucion = '';
  int type = 1;
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      key: scaffoldkey,
      appBar: _createAppBar(),
      body: _createBody(size),
    );
  }

  Widget _createBody(Size size) {
    return SingleChildScrollView(
      child: Container(
        child: Column(
          children: [
            _createType(size),
            Divider(color: Colors.black, thickness: 2),
            Container(
              margin: EdgeInsets.symmetric(vertical: 15),
              width: size.width * 0.85,
              child: (type == 3)
                  ? _createExternTransfer(size)
                  : (type == 2) ? _createInternTransfer() : Container(),
            ),
          ],
        ),
      ),
    );
  }

  Widget _createInternTransfer() {
    return Form(
      key: formkey2,
      child: Column(
        children: [
          _createField('# Cuenta', TextInputType.number, false, user, 'cuenta'),
          _createField('Nombre', TextInputType.text, false, user, 'nombre'),
          _createField(
              'Correo', TextInputType.emailAddress, false, user, 'correo'),
          _createField('Monto', TextInputType.numberWithOptions(decimal: true),
              false, user, ''),
          SizedBox(
            height: 30,
          ),
          RaisedButton(
            color: Color(0xff2D6A67),
            child: Text(
              'Aceptar',
              style: TextStyle(color: Colors.white),
            ),
            onPressed: _submit2,
          ),
        ],
      ),
    );
  }

  Widget _createExternTransfer(Size size) {
    return Form(
      key: formkey,
      child: Column(
        children: [
          _createComboBox(size, 'Institución',
              ['Institución A', 'Institución B'], 'institucion'),
          _createComboBox(
              size, 'Tipo Cuenta', ['Ahorro', 'Corriente'], 'tipoCuenta'),
          _createField('# Cuenta', TextInputType.text, false, user, 'cuenta'),
          _createField('Monto', TextInputType.numberWithOptions(decimal: true),
              false, user, 'monto'),
          _createComboBox(
              size, 'Identificación', ['Cédula', 'Ruc'], 'tipoIdentificacion'),
          _createField('# Identificación', TextInputType.number, false, user,
              'identificacion'),
          _createField('Nombre', TextInputType.text, false, user, 'nombre'),
          _createField(
              'Correo', TextInputType.emailAddress, false, user, 'correo'),
          _createField('Concepto', TextInputType.text, false, user, 'concepto'),
          SizedBox(
            height: 30,
          ),
          RaisedButton(
            color: Color(0xff2D6A67),
            child: Text(
              'Aceptar',
              style: TextStyle(color: Colors.white),
            ),
            onPressed: _submit,
          ),
        ],
      ),
    );
  }

  _submit() async {
    final userService = UserServices();
    if (!formkey.currentState.validate()) return;
    formkey.currentState.save();
    bool ok = await userService.transfer(
        'Externa',
        institucion,
        tipoCuenta,
        user.transferTo,
        user.monto,
        user.identificacion,
        user.nombre,
        user.email,
        user.concepto);
    if (ok) {
      await new Future.delayed(Duration(milliseconds: 1500));
      await utils.mostrarSnackBar(
          'Transferencia exitosa', Colors.green, scaffoldkey);
      await new Future.delayed(Duration(seconds: 2));
    } else {
      return utils.mostrarSnackBar(
          'Fallo transferencia', Colors.red, scaffoldkey);
    }
  }

  _submit2() async {
    final userService = UserServices();
    if (!formkey2.currentState.validate()) return;
    formkey2.currentState.save();
    bool ok = await userService.transfer('Interna', '', '', user.transferTo,
        user.monto, '', user.nombre, user.email, '');
    if (ok) {
      await new Future.delayed(Duration(milliseconds: 1500));
      await utils.mostrarSnackBar(
          'Transferencia exitosa', Colors.green, scaffoldkey);
      await new Future.delayed(Duration(seconds: 2));
    } else {
      return utils.mostrarSnackBar(
          'Fallo transferencia', Colors.red, scaffoldkey);
    }
  }

  Widget _createType(Size size) {
    return Center(
      child: Container(
        margin: EdgeInsets.symmetric(vertical: 15),
        padding: EdgeInsets.symmetric(horizontal: 10),
        width: size.width * 0.90,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10.0),
          color: Color(0xff37B0A9),
        ),
        child: DropdownButtonFormField(
          hint: Text(
            'Seleccionar',
            style: TextStyle(color: Colors.black),
          ),
          items: <String>[
            'Transferencia Interna',
            'Transferencia Externa',
          ].map<DropdownMenuItem<String>>((String value) {
            return DropdownMenuItem<String>(
              child: Text(value),
              value: value,
            );
          }).toList(),
          onChanged: (value) {
            setState(() {
              if (value == 'Transferencia Interna') {
                type = 2;
              } else if (value == 'Transferencia Externa') {
                type = 3;
              }
            });
          },
        ),
      ),
    );
  }

  Widget _createComboBox(
      Size size, String text, List<String> list, String type) {
    return Center(
      child: Container(
        margin: EdgeInsets.symmetric(vertical: 5),
        // padding: EdgeInsets.symmetric(horizontal: 10),
        width: size.width * 0.95,
        child: DropdownButtonFormField(
          hint: Text(
            text,
            style: TextStyle(color: Colors.black),
          ),
          items: list.map<DropdownMenuItem<String>>((String value) {
            return DropdownMenuItem<String>(
              child: Text(value),
              value: value,
            );
          }).toList(),
          onChanged: (value) {
            switch (type) {
              case 'tipoCuenta':
                tipoCuenta = value;
                break;
              case 'institucion':
                institucion = value;
                break;
              default:
            }
          },
        ),
      ),
    );
  }

  Widget _createField(String text, TextInputType type, bool obscure,
      UserModel user, String typeV) {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 5),
      child: TextFormField(
        obscureText: obscure,
        style: TextStyle(color: Colors.black),
        keyboardType: type,
        decoration: InputDecoration(
            hintText: text,
            hintStyle: TextStyle(color: Colors.black),
            border: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.black),
            ),
            focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black, width: 2))),
        validator: (value) {
          switch (typeV) {
            case 'nombre':
              if (value.length <= 3) {
                return 'La contraseña debe tener al menos 3 digitos';
              } else {
                return null;
              }
              break;
            case 'concepto':
              if (value.length <= 5) {
                return 'La contraseña debe tener al menos 3 digitos';
              } else {
                return null;
              }
              break;
            case 'correo':
              Pattern pattern =
                  r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
              RegExp regExp = new RegExp(pattern);
              if (regExp.hasMatch(value)) {
                return null;
              } else {
                return 'El email es invalido';
              }
              break;
            default:
              return null;
          }
        },
        onSaved: (value) {
          switch (typeV) {
            case 'nombre':
              user.nombre = value;
              break;
            case 'concepto':
              user.concepto = value;
              break;
            case 'correo':
              user.email = value;
              break;
            case 'cuenta':
              user.transferTo = value;
              break;
            case 'monto':
              user.monto = value;
              break;
            case 'identificacion':
              user.identificacion = value;
              break;
            default:
              return null;
          }
        },
      ),
    );
  }

  AppBar _createAppBar() {
    return AppBar(
      centerTitle: false,
      backgroundColor: Color(0xff2D6A67),
      title: Image.asset(
        'assets/logo.png',
        height: 50,
      ),
      actions: [
        IconButton(
            icon: Icon(Icons.close),
            onPressed: () {
              Navigator.pushReplacementNamed(context, 'login');
            })
      ],
    );
  }
}
