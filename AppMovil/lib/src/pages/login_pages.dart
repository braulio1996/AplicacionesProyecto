import 'package:appdis/src/models/user_model.dart';
import 'package:appdis/src/services/user_services.dart';
import 'package:flutter/material.dart';
import 'package:appdis/src/pages/utils.dart' as utils;

class Loginpage extends StatefulWidget {
  const Loginpage({Key key}) : super(key: key);

  @override
  _LoginpageState createState() => _LoginpageState();
}

class _LoginpageState extends State<Loginpage> {
  final user = UserModel();
  final formkey = GlobalKey<FormState>();
  final scaffoldkey = GlobalKey<ScaffoldState>();
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      key: scaffoldkey,
      backgroundColor: Color(0xff37B0A9),
      body: _createBody(size, context),
    );
  }

  Widget _createBody(Size size, BuildContext context) {
    return SingleChildScrollView(
      child: SafeArea(
          child: Container(
              height: 500,
              //margin: EdgeInsets.symmetric(vertical: 50),
              width: size.width,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  _createHeader(),
                  _createForm(size, context),
                  _createFooter()
                ],
              ))),
    );
  }

  Column _createFooter() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text('No tienes una cuenta?'),
        Text('Acercate a nuestras instalaciones y te ayudaremos')
      ],
    );
  }

  Container _createForm(Size size, BuildContext context) {
    return Container(
      width: size.width * 0.80,
      child: Form(
        key: formkey,
        child: Column(
          children: [
            _createField('Correo', TextInputType.emailAddress, false, user),
            _createField('Contraseña', TextInputType.text, true, user),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                Icon(Icons.lock, color: Color.fromRGBO(0, 0, 0, 0.5)),
                FlatButton(
                  child: Text('Olvidé mi contraseña',
                      style: TextStyle(color: Color.fromRGBO(0, 0, 0, 0.5))),
                  onPressed: () {
                    Navigator.pushNamed(context, 'recover');
                  },
                ),
              ],
            ),
            SizedBox(height: 30),
            RaisedButton(
              color: Color(0xff2D6A67),
              child:
                  Text('INICIAR SESIÓN', style: TextStyle(color: Colors.white)),
              // onPressed: () {
              //   Navigator.pushReplacementNamed(context, 'tabs');
              // },
              onPressed: _submit,
            ),
          ],
        ),
      ),
    );
  }

  _submit() async {
    final userService = UserServices();
    if (!formkey.currentState.validate()) return;
    formkey.currentState.save();
    bool ok = await userService.userLogin(user.email, user.password);
    if (ok) {
      Navigator.pushReplacementNamed(context, 'tabs');
    } else {
      print('usuario o password incorrectos');
      return utils.mostrarSnackBar(
          'Usuario incorrecto', Colors.red, scaffoldkey);
    }
  }

  TextFormField _createField(
      String text, TextInputType type, bool obscure, UserModel user) {
    return TextFormField(
      obscureText: obscure,
      style: TextStyle(color: Colors.white),
      keyboardType: type,
      decoration: InputDecoration(
          hintText: text,
          hintStyle: TextStyle(color: Colors.white),
          border: UnderlineInputBorder(
            borderSide: BorderSide(color: Colors.black),
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
    );
  }

  Column _createHeader() {
    return Column(
      children: [
        Container(
          child: Image.asset('assets/logo.png'),
        ),
        SizedBox(height: 20),
        Text(
          'ACCESO AL SISTEMA TRANSACCIONAL',
          style: TextStyle(fontWeight: FontWeight.bold),
        )
      ],
    );
  }
}
