import 'package:flutter/material.dart';

class CreditPage extends StatelessWidget {
  const CreditPage({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    final style = TextStyle(fontSize: 18, fontWeight: FontWeight.bold);

    return Scaffold(
      appBar: _createAppBar(context),
      body: _createBody(size, style),
    );
  }

  Column _createBody(Size size, TextStyle style) {
    return Column(
      children: [
        _createHeader(size, style),
        _createTable(size),
      ],
    );
  }

  Container _createTable(Size size) {
    final style = TextStyle(fontSize: 18, fontWeight: FontWeight.bold);
    return Container(
      width: size.width * 0.90,
      height: size.height * 0.65,
      child:
          Column(mainAxisAlignment: MainAxisAlignment.spaceAround, children: [
        Column(
          children: [
            Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
              Text('#'),
              Text('Fecha'),
              Text('Saldo'),
              Text('Estado'),
            ]),
            Divider(
              color: Colors.black,
              thickness: 2,
            ),
            Container(
              height: size.height * 0.60,
              child: ListView(
                children: [
                  _createRow('1', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('2', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('3', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('4', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('1', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('2', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('3', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('1', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('2', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('3', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('1', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('2', '16/05/2020', '650.00', 'Pendiente', size),
                  _createRow('3', '16/05/2020', '650.00', 'Pendiente', size),
                ],
              ),
            ),
          ],
        )
      ]),
    );
  }

  Widget _createRow(
      String numCouta, String fecha, String saldo, String estado, Size size) {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 10),
      width: size.width * 0.90,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(numCouta),
          Text(fecha),
          Text(saldo),
          Text(estado),
        ],
      ),
    );
  }

  Center _createHeader(Size size, TextStyle style) {
    return Center(
      child: Container(
        margin: EdgeInsets.symmetric(vertical: 20),
        width: size.width * 0.90,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text('Créditos', style: style),
            Text('\$ 1500.00', style: style),
          ],
        ),
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
