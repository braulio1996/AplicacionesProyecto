import 'package:appdis/src/pages/credit_pages.dart';
import 'package:appdis/src/pages/home_pages.dart';
import 'package:appdis/src/pages/transfer_pages.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class TabsPage extends StatelessWidget {
  const TabsPage({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => new NavegacionModel(),
      child: Scaffold(
        body: _Paginas(),
        bottomNavigationBar: _Navegacion(),
      ),
    );
  }
}

class _Navegacion extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //como si fuera un singleton
    final navegacionModel = Provider.of<NavegacionModel>(context);
    // final newsService = Provider.of<NewsService>(context);

    return BottomNavigationBar(
      unselectedItemColor: Colors.white,
      backgroundColor: Color(0xff2D6A67),
      type: BottomNavigationBarType.fixed,
      currentIndex: navegacionModel.paginaActual,
      onTap: (index) => navegacionModel.paginaActual = index,
      selectedItemColor: Colors.grey,
      items: [
        BottomNavigationBarItem(
          backgroundColor: Color(0xff2E57A6),
          icon: Icon(
            Icons.attach_money,
            // color: Colors.white,
          ),
          title: Text('Transferencias'),
        ),
        BottomNavigationBarItem(
          icon: Icon(
            Icons.home,
            // color: Colors.white,
          ),
          title: Text('Inicio'),
        ),
        BottomNavigationBarItem(
          icon: Icon(
            Icons.credit_card,
            // color: Colors.white,
          ),
          title: Text('Créditos'),
        ),
      ],
    );
  }
}

class _Paginas extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final navegacionModel = Provider.of<NavegacionModel>(context);
    return PageView(
      controller: navegacionModel.pageController,
      physics: NeverScrollableScrollPhysics(),
      onPageChanged: (index) => navegacionModel.paginaActual = index,
      children: <Widget>[
        TransferPage(),
        HomePage(),
        CreditPage(),
      ],
    );
  }
}

class NavegacionModel with ChangeNotifier {
  int _paginaActual = 1;
  PageController _pageController = new PageController();

  int get paginaActual => this._paginaActual;

  set paginaActual(int valor) {
    this._paginaActual = valor;
    _pageController.animateToPage(valor,
        duration: Duration(milliseconds: 250), curve: Curves.easeOut);
    notifyListeners();
  }

  PageController get pageController => this._pageController;
}
