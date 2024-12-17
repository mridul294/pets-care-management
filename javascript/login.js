angular.module('PetsApp', []).controller('LoginController', function ($scope) {
    // Login credentials and feedback messages
    $scope.credentials = {};
    $scope.successMessage = '';
    $scope.errorMessage = '';

    // Simulated user data (replace this with actual server-side logic)
    const userDatabase = [
        { username: 'testuser', password: 'password123' },
        { username: 'john', password: 'doe123' }
    ];

    // Login function
    $scope.loginUser = function () {
        $scope.successMessage = '';
        $scope.errorMessage = '';

        const user = userDatabase.find(
            u =>
                u.username === $scope.credentials.username &&
                u.password === $scope.credentials.password
        );

        if (user) {
            $scope.successMessage = "Login successful! Redirecting...";
            console.log("Logged in User:", user);

            // Redirect to the homepage or dashboard
            setTimeout(() => {
                window.location.href = 'home.html';
            }, 2000);
        } else {
            $scope.errorMessage = "Invalid username or password. Please try again.";
        }
    };
});
