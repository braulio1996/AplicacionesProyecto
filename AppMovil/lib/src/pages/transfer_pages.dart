import 'package:flutter/material.dart';

class TransferPage extends StatefulWidget {
  const TransferPage({Key key}) : super(key: key);

  @override
  _TransferPageState createState() => _TransferPageState();
}

class _TransferPageState extends State<TransferPage> {
  int type = 1;
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
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

  Column _createInternTransfer() {
    return Column(
      children: [
        _createField('# Cuenta', TextInputType.number, false),
        _createField('Nombre', TextInputType.text, false),
        _createField('Correo', TextInputType.emailAddress, false),
        _createField(
            'Monto', TextInputType.numberWithOptions(decimal: true), false),
        SizedBox(
          height: 30,
        ),
        RaisedButton(
          color: Color(0xff2D6A67),
          child: Text(
            'Aceptar',
            style: TextStyle(color: Colors.white),
          ),
          onPressed: () {},
        ),
      ],
    );
  }

  Column _createExternTransfer(Size size) {
    return Column(
      children: [
        _createComboBox(
            size, 'Institución', ['Institución A', 'Institución B']),
        _createComboBox(size, 'Tipo Cuenta', ['Ahorro', 'Corriente']),
        _createField('# Cuenta', TextInputType.text, false),
        _createField(
            'Monto', TextInputType.numberWithOptions(decimal: true), false),
        _createComboBox(size, 'Identificación', ['Cédula', 'Ruc']),
        _createField('# Identificación', TextInputType.number, false),
        _createField('Nombre', TextInputType.text, false),
        _createField('Correo', TextInputType.emailAddress, false),
        _createField('Concepto', TextInputType.text, false),
        SizedBox(
          height: 30,
        ),
        RaisedButton(
          color: Color(0xff2D6A67),
          child: Text(
            'Aceptar',
            style: TextStyle(color: Colors.white),
          ),
          onPressed: () {},
        ),
      ],
    );
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

  Widget _createComboBox(Size size, String text, List<String> list) {
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
          onChanged: (value) {},
        ),
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
            hintStyle: TextStyle(color: Colors.black),
            border: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.black),
            ),
            focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black, width: 2))),
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
