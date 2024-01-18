import 'dart:io';

class User {
  double id;
  String name;
  String password;
  String email;
  int age;

  User(this.id, this.name, this.password, this.email, this.age);

  String greet() => "Hola, me llamo $name";
}

class Product {
  int id;
  String name;
  double price;
  String description;

  Product(this.id, this.name, this.price, this.description);

  String display() =>
      "Product: $name, price: $price, description: $description, id: $id";
}

class ProductRepository {
  List<Product> products = [
    Product(1, "product1", 10, "description"),
    Product(2, "product2", 20, "description"),
    Product(3, "product3", 40, "description")
  ];

  Product getProductById(int id) {
    var p = products.firstWhere((element) => element.id == id,
        orElse: () => throw Exception("Product not found"));
    return p;
  }

  String display() {
    var s = "";
    for (var p in products) {
      s += p.display() + "\n";
    }
    return s;
  }

  void addProduct(Product p) {
    products.add(p);
  }

  void removeProduct(Product p) {
    products.remove(p);
  }

  void updateProduct(Product p) {
    var index = products.indexWhere((element) => element.id == p.id);
    products[index] = p;
  }
}

void main() {
  var repo = ProductRepository();
  String menu =
      "1. Add product\n2. Remove product\n3. Update product\n4. Display products\n5. Exit\n";
  int option = -1;
  while (option != 0) {
    print(menu);
    option = int.parse(stdin.readLineSync()!);
    switch (option) {
      case 1:
        print("Enter product id");
        var id = int.parse(stdin.readLineSync()!);
        print("Enter product name");
        var name = stdin.readLineSync()!;
        print("Enter product price");
        var price = double.parse(stdin.readLineSync()!);
        print("Enter product description");
        var description = stdin.readLineSync()!;
        var p = Product(id, name, price, description);
        repo.addProduct(p);
        break;
      case 2:
        print("Enter product id");
        var id = int.parse(stdin.readLineSync()!);
        var p = repo.getProductById(id);
        repo.removeProduct(p);
        break;
      case 3:
        print("Enter product id");
        var id = int.parse(stdin.readLineSync()!);
        print("Enter product name");
        var name = stdin.readLineSync()!;
        print("Enter product price");
        var price = double.parse(stdin.readLineSync()!);
        print("Enter product description");
        var description = stdin.readLineSync()!;
        var p = Product(id, name, price, description);
        repo.updateProduct(p);
        break;
      case 4:
        print(repo.display());
        break;
      case 5:
        option = 0;
        break;
      default:
        print("Invalid option");
        break;
    }
  }
}
