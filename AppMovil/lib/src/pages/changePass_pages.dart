import 'package:flutter/material.dart';

class ChangePasPass extends StatelessWidget {
  const ChangePasPass({Key key}) : super(key: key);

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
            _createTitle(),
            _createField('Ingrese contraseña actual', TextInputType.text, true),
            _createField('Ingrese contraseña nueva', TextInputType.text, true),
            _createField(
                'Verificar contraseña nueva', TextInputType.text, true),
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

  Container _createTitle() {
    final style = TextStyle(fontSize: 18, fontWeight: FontWeight.bold);
    return Container(
      child: Text(
        'Cambiar Contraseña',
        style: style,
      ),
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
