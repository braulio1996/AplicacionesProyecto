import 'dart:convert';

import 'package:appdis/src/pages/preferences.dart';
import 'package:http/http.dart' as http;

class UserServices {
  final _URL = "http://http://35.203.12.217//AplicacionesBanco/services/users";

  UserServices() {}

  Future<bool> userLogin(String email, String password) async {
    final url = '$_URL/Login?correo=${email}&clave=${password}';
    final response = await http.get(url,
        headers: <String, String>{"Content-Type": "application/json"});
    try {
      final Map<String, dynamic> decodedData = json.decode(response.body);
      print(decodedData);
      if (decodedData['codigo'] == 1) {
        final preferences = new Preferences();
        preferences.cuenta = decodedData['mensaje'];
        return true;
      } else {
        return false;
      }
    } catch (Exception) {
      return false;
    }
  }

  Future<bool> userRecoverPassword(String email) async {
    final url = '$_URL/Password?correo=${email}';
    final response = await http.get(url,
        headers: <String, String>{"Content-Type": "application/json"});
    try {
      final Map<String, dynamic> decodedData = json.decode(response.body);
      print(decodedData);
      if (decodedData['codigo'] == 1) {
        return true;
      } else {
        return false;
      }
    } catch (Exception) {
      return false;
    }
  }

  Future<bool> transfer(
      String tipo,
      String institucion,
      String tipoCuenta,
      String numeroCuenta,
      String monto,
      String identificacion,
      String nombre,
      String correo,
      String concepto) async {
    final url = '$_URL/transferencia';
    final _preferences = new Preferences();
    dynamic body = {
      "tipo": tipo,
      "fecha": DateTime.now().toString(),
      "institucion": institucion,
      "tipoCuenta": tipoCuenta,
      "numeroCuenta": numeroCuenta,
      "monto": monto,
      "identificacion": identificacion,
      "nombre": nombre,
      "correo": correo,
      "concepto": concepto,
      "cuentaOrigen": _preferences.cuenta,
    };

    final response = await http.post(url,
        body: jsonEncode(body),
        headers: <String, String>{"Content-Type": "application/json"});
    try {
      final Map<String, dynamic> decodedData = json.decode(response.body);
      print(decodedData);
      if (decodedData['codigo'] == 1) {
        return true;
      } else {
        return false;
      }
    } catch (Exception) {
      return false;
    }
  }
}
