import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: _createAppBar(context),
      body: _createBody(size, context),
    );
  }

  Column _createBody(Size size, BuildContext context) {
    return Column(
      children: [
        _createHeader(size, context),
        Divider(
          color: Colors.black,
          thickness: 2,
        ),
        _createTable(size),
      ],
    );
  }

  Container _createTable(Size size) {
    final style = TextStyle(fontSize: 18, fontWeight: FontWeight.bold);
    return Container(
      width: size.width * 0.90,
      height: size.height * 0.55,
      child:
          Column(mainAxisAlignment: MainAxisAlignment.spaceAround, children: [
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
          Text('Cuenta Ahorros', style: style),
          Text('\$ 1500.00', style: style),
        ]),
        Column(
          children: [
            Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
              Text('Últimos movimientos'),
              Text('Saldo'),
            ]),
            Divider(
              color: Colors.black,
              thickness: 2,
            ),
            Container(
              height: size.height * 0.40,
              child: ListView(
                children: [
                  _createRow(
                      '16/05/2020', 'Transferencia a Diego Ochoa', '650.50'),
                  _createRow('16/05/2020', 'Depósito', '650.50'),
                  _createRow('16/05/2020', 'Retiro', '650.50'),
                  _createRow(
                      '16/05/2020', 'Transferencia a XXXXXXXX', '650.50'),
                ],
              ),
            ),
          ],
        )
      ]),
    );
  }

  ListTile _createRow(String fecha, String descripcion, String saldo) {
    return ListTile(
      dense: true,
      contentPadding: EdgeInsets.symmetric(horizontal: 0),
      title: Text(fecha),
      subtitle: Text(descripcion),
      trailing: Text(saldo),
    );
  }

  Center _createHeader(Size size, BuildContext context) {
    return Center(
      child: Container(
        margin: EdgeInsets.symmetric(vertical: 15),
        width: size.width * 0.95,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20.0),
          color: Color(0xff37B0A9),
        ),
        child: ListTile(
            isThreeLine: true,
            title: Text('Juan Perez',
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20)),
            subtitle: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('XXXXXXXXXXXXX'),
                Text('Saldo: \$ 1500.00'),
              ],
            ),
            trailing: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                GestureDetector(
                  child: Column(
                    children: [
                      Icon(Icons.settings, color: Colors.black),
                      Text('Configurar'),
                    ],
                  ),
                  onTap: () {
                    Navigator.pushNamed(context, 'change');
                  },
                ),
              ],
            )),
      ),
    );
  }

  AppBar _createAppBar(BuildContext context) {
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
