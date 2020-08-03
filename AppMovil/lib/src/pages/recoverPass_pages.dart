import 'package:flutter/material.dart';

class RecoverPasPass extends StatelessWidget {
  const RecoverPasPass({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      backgroundColor: Color(0xff37B0A9),
      body: Center(
          child: Container(
        width: size.width * 0.80,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _creteTitle(),
            _createField('Correo', TextInputType.emailAddress, false),
            SizedBox(height: 30),
            _createButton(context)
          ],
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
      onPressed: () {
        Navigator.pop(context);
      },
    );
  }

  Container _creteTitle() {
    return Container(
      child: Text(
          'Ingrese su correo electronico y le enviaremos su nueva contraseña'),
    );
  }

  Widget _createField(String text, TextInputType type, bool obscure) {
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
      ),
    );
  }
}
