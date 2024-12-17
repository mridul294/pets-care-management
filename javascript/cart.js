angular.module('PetsApp', []).controller('CartController', function ($scope) {
    $scope.cart = JSON.parse(localStorage.getItem('cart')) || [];

    $scope.totalAmount = function () {
        return $scope.cart.reduce((sum, item) => sum + item.price, 0);
    };

    $scope.removeFromCart = function (item) {
        const index = $scope.cart.indexOf(item);
        if (index !== -1) {
            $scope.cart.splice(index, 1);
            localStorage.setItem('cart', JSON.stringify($scope.cart));
        }
    };

    $scope.processPayment = function () {
        alert("Payment processed successfully!");
        $scope.cart = []; // Clear the cart after payment
        localStorage.setItem('cart', JSON.stringify($scope.cart)); // Update localStorage
        window.location.href = "confirmation.html"; // Redirect to confirmation page
    };
});
