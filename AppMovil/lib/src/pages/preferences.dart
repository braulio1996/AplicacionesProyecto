import 'package:shared_preferences/shared_preferences.dart';

class Preferences {
  static final Preferences _instancia = new Preferences._internal();

  factory Preferences() {
    return _instancia;
  }

  Preferences._internal();

  SharedPreferences _prefs;

  initPreferences() async {
    this._prefs = await SharedPreferences.getInstance();
  }

  get cuenta {
    return _prefs.getString('cuenta') ?? '';
  }

  set cuenta(String cuenta) {
    _prefs.setString('cuenta', cuenta);
  }
}
