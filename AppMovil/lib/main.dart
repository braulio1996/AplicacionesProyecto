import 'package:appdis/src/pages/changePass_pages.dart';
import 'package:appdis/src/pages/credit_pages.dart';
import 'package:appdis/src/pages/home_pages.dart';
import 'package:appdis/src/pages/login_pages.dart';
import 'package:appdis/src/pages/preferences.dart';
import 'package:appdis/src/pages/recoverPass_pages.dart';
import 'package:appdis/src/pages/tabs_pages.dart';
import 'package:appdis/src/pages/transfer_pages.dart';
import 'package:flutter/material.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final _preferences = new Preferences();
  await _preferences.initPreferences();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Material App',
      initialRoute: 'login',
      routes: {
        'login': (BuildContext context) => Loginpage(),
        'tabs': (BuildContext context) => TabsPage(),
        'home': (BuildContext context) => HomePage(),
        'credit': (BuildContext context) => CreditPage(),
        'transfer': (BuildContext context) => TransferPage(),
        'recover': (BuildContext context) => RecoverPasPass(),
        'change': (BuildContext context) => ChangePasPass(),
      },
    );
  }
}
