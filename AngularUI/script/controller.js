var app = angular.module("mymodule", ['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/Show', {
        templateUrl: '/Pages/showbook.html',
        controller: 'datacontroller'
    }).when('/Addbook', {
        templateUrl: '/Pages/Addbook.html',
        controller: 'postserviceCtrl'
    }).when('/Updatebook', {
        templateUrl: '/Pages/edit.html',
        controller: 'updatecontroller'
    }).when('/Admin', {
        templateUrl: '/Pages/adminlogin.html',
        controller: 'logincontroller'
    }).when('/Layout', {
        templateUrl: '/Pages/layout.html',
        controller: 'logincontroller'
    }).when('/Student', {
        templateUrl: '/Pages/StudentDetail.html',
        controller: 'studentcontroller'
    }).when('/StudentLogin', {
        templateUrl: '/Pages/StudentDetail.html',
        controller: 'Studentlogincontroller'
    })
}]);

app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(false);
    $locationProvider.hashPrefix('');
}]);

//datacontroller//
app.controller("datacontroller", function($scope, $http) {
    const URL1 = "http://localhost:8080/showbook"
    $http.get(URL1).then(
        (response) => {
            console.log(response.data);
            $scope.Books = response.data.books;
        },
        (error) => {
            console.log(response);
        }
    )
});

//logincontroller//
app.controller('logincontroller', function($scope, $http) {
    $scope.name = null;
    $scope.pass = null;
    $scope.postdata = function(name, pass) {
        console.log(name, pass);
        var data = {
            "username": name,
            "password": pass
        };
        URL = "http://localhost:8080/loginpost";
        $http.post(URL, JSON.stringify(data)).then(
            (response) => {
                console.log(response.data);
                if (angular.equals(response.data, "login not Successful")) {
                    alert("login not Successful");
                } else {
                    alert("login Successful")
                    window.location.href = "./Pages/layout.html"
                }
            },
            (error) => {
                alert("User Not Exixsts")
            }
        )
    };

});


//postcontroller//             
app.controller('postserviceCtrl', function($scope, $http) {
    $scope.title = null;
    $scope.author = null;
    $scope.copies = null;
    $scope.ISBN = null;


    $scope.postdata = function(ISBN, title, author, copies) {
        console.log(title, author, copies);
        var data = {
            b_id: ISBN,

            b_title: title,

            b_author: author,

            b_copies: copies

        };
        var URL = 'http://localhost:8080/newbook';
        $http.post(URL, JSON.stringify(data)).then(function(response) {
            if (response.data)
                alert("Book Is Added Successfully");
        }, function(response) {
            $scope.msg = "Service not Exists";
        });

    };

});

//deletecontroller//
app.controller('deletecontroller', function($scope, $http) {
    $scope.deletebook = function(book) {
        alert("Do you want to delete this book?");
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/delete/' + book.b_id
        }).then(function successCallback(response) {

            alert("book has been deleted Successfully");
            //var index = $scope.users.indexOf(book);
            //$scope.books.splice(index, 1);

        }, function errorCallback(response) {
            console.log(response);
            alert("Error. while deleting book Try Again!");

        });

    };
});
//updatecontroller//
app.controller('updatecontroller', function($scope, $http) {

    $scope.id = null;

    $scope.title = null;

    $scope.author = null;

    $scope.copies = null;

    $scope.updatedata = function(id, title, author, copies) {
        id = parseInt(id);
        console.log(id, title, author, copies);
        var data = {
            b_id: id,

            b_title: title,

            b_author: author,

            b_copies: copies

        };
        var URL = 'http://localhost:8080/update';
        $http.put(URL, JSON.stringify(data)).then(function(response) {
            console.log(JSON.stringify(data));

            if (response.data) {
                alert("Book Is updated Successfully");
                console.log(response.data);
            }
        }, function(response) {
            $scope.msg = "Service not Exists";

            $scope.statusval = response.status;

            $scope.statustext = response.statusText;

            $scope.headers = response.headers();
        });

    };

});

app.controller("studentcontroller", function($scope, $http) {
    const URL1 = "http://localhost:8080/students"
    $http.get(URL1).then(
        (response) => {
            console.log(response.data.students);
            $scope.Students = response.data.students;
        },
        (error) => {
            console.log(response);
        }
    )
});

//Studentlogincontroller//
app.controller('Studentlogincontroller', function($scope, $http) {
    $scope.name = null;
    $scope.pass = null;
    $scope.postdata = function(name, pass) {
        console.log(name, pass);
        var data = {
            "username": name,
            "password": pass
        };
        URL = "http://localhost:8080/loginpost";
        $http.post(URL, JSON.stringify(data)).then(
            (response) => {
                console.log(response.data);
                if (angular.equals(response.data, "login not Successful")) {
                    alert("login not Successful");
                } else {
                    alert("login Successful")
                    window.location.href = "./layout.html"
                }
            },
            (error) => {
                alert("User Not Exixsts")
            }
        )
    };

});