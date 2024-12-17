// AngularJS App
angular.module('PetsApp', []).controller('PetsController', function ($scope) {
    $scope.allProducts = [
        { name: "Dog Toy", category: "Toys", price: 10, image: "dog-toy.jpg" },
        { name: "Cat Food", category: "Food", price: 15, image: "cat-food.jpg" },
        { name: "Bird Cage", category: "Accessories", price: 25, image: "bird-cage.jpg" },
        { name: "Fish Tank", category: "Aquarium", price: 50, image: "fish-tank.jpg" },
        { name: "Rabbit Grooming Kit", category: "Grooming", price: 20, image: "rabbit-grooming-kit.jpg" },
        { name: "Pet Vitamins", category: "Medicines", price: 12, image: "pet-vitamins.jpg" }
    ];
    $scope.cart = JSON.parse(localStorage.getItem('cart')) || [];
    $scope.showCart = false;
    $scope.filteredProducts = $scope.allProducts;

    $scope.searchProducts = function () {
        $scope.filteredProducts = $scope.allProducts.filter(product =>
            product.name.toLowerCase().includes($scope.searchQuery.toLowerCase())
        );
    };

    $scope.addToCart = function (product) {
        $scope.cart.push(product);
        localStorage.setItem('cart', JSON.stringify($scope.cart));
    };

    $scope.toggleCart = function () {
        $scope.showCart = !$scope.showCart;
    };

    $scope.proceedToBilling = function () {
        window.location.href = "cart.html";
    };

    $scope.filterByCategory = function () {
        if ($scope.selectedCategory) {
            $scope.filteredProducts = $scope.allProducts.filter(product =>
                product.category === $scope.selectedCategory
            );
        } else {
            $scope.filteredProducts = $scope.allProducts;
        }
    };
});

// JavaScript for Additional Features
document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.querySelector('.search-input');
    searchInput.addEventListener('focus', function () {
        this.style.borderColor = '#32CD32';
    });
    searchInput.addEventListener('blur', function () {
        this.style.borderColor = '#ddd';
    });
});
