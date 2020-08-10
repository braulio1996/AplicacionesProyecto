import 'package:appdis/src/models/user_model.dart';
import 'package:appdis/src/services/user_services.dart';
import 'package:flutter/material.dart';
import 'package:appdis/src/pages/utils.dart' as utils;

class RecoverPasPass extends StatefulWidget {
  const RecoverPasPass({Key key}) : super(key: key);

  @override
  _RecoverPasPassState createState() => _RecoverPasPassState();
}

class _RecoverPasPassState extends State<RecoverPasPass> {
  final user = UserModel();
  final formkey = GlobalKey<FormState>();
  final scaffoldkey = GlobalKey<ScaffoldState>();
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      key: scaffoldkey,
      backgroundColor: Color(0xff37B0A9),
      body: Center(
          child: Form(
        key: formkey,
        child: Container(
          width: size.width * 0.80,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              _creteTitle(),
              _createField('Correo', TextInputType.emailAddress, false, user),
              SizedBox(height: 30),
              _createButton(context)
            ],
          ),
        ),
      )),
    );
  }

  RaisedButton _createButton(BuildContext context) {
    return RaisedButton(
      color: Color(0xff2D6A67),
      child: Text(
        'Confirmar',
        style: TextStyle(color: Colors.white),
      ),
      onPressed: _submit,
    );
  }

  _submit() async {
    final userService = UserServices();
    if (!formkey.currentState.validate()) return;
    formkey.currentState.save();
    bool ok = await userService.userRecoverPassword(user.email);
    if (ok) {
      await new Future.delayed(Duration(milliseconds: 1500));
      await utils.mostrarSnackBar(
          'Su contrasena fue enviada a su correo!', Colors.green, scaffoldkey);
      await new Future.delayed(Duration(seconds: 2));
      Navigator.pop(context);
    } else {
      return utils.mostrarSnackBar(
          'Correo incorrecto', Colors.red, scaffoldkey);
    }
  }

  Container _creteTitle() {
    return Container(
      child: Text(
          'Ingrese su correo electronico y le enviaremos su nueva contraseña'),
    );
  }

  Widget _createField(
      String text, TextInputType type, bool obscure, UserModel user) {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 5),
      child: TextFormField(
        obscureText: obscure,
        style: TextStyle(color: Colors.black),
        keyboardType: type,
        decoration: InputDecoration(
            hintText: text,
            hintStyle: TextStyle(color: Colors.white),
            border: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.white),
            ),
            focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black, width: 2))),
        validator: (value) {
          if (obscure) {
            if (value.length <= 8) {
              return 'La contraseña debe tener al menos 8 digitos';
            } else {
              return null;
            }
          } else {
            Pattern pattern =
                r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
            RegExp regExp = new RegExp(pattern);
            if (regExp.hasMatch(value)) {
              return null;
            } else {
              return 'El email es invalido';
            }
          }
        },
        onSaved: (value) {
          if (obscure) {
            user.password = value;
          } else {
            user.email = value;
          }
        },
      ),
    );
  }
}
