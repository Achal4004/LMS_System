const URL1 = "http://localhost:8080/showbook"

var app = angular.module("mymodule", []);
app.controller("datacontroller", ($scope, $http) => {
    console.log("App Loaded");

    $http.get(URL1).then(
        (response) => {
            console.log(response.data);
            $scope.Books = response.data.books;
        },
        (error) => {
            console.log(response);
        }
    )

})

app.controller('postserviceCtrl', function($scope, $http) {
    // console.log("hi");
    $scope.title = null;

    $scope.author = null;

    $scope.copies = null;

    $scope.postdata = function(title, author, copies) {
        console.log(title, author, copies);
        var data = {

            b_title: title,

            b_author: author,

            b_copies: copies

        };
        var URL = 'http://localhost:8080/newbook';
        $http.post(URL, JSON.stringify(data)).then(function(response) {
            console.log(JSON.stringify(data));

            if (response.data)
                alert("Book Is Added Successfully");

        }, function(response) {

            $scope.msg = "Service not Exists";

            $scope.statusval = response.status;

            $scope.statustext = response.statusText;

            $scope.headers = response.headers();

        });

    };

});
app.controller('deletecontroller', function($scope, $http) {

    $scope.deletebook = function(book) {
        alert("Do you want to delete your book?");
        $http({

            method: 'DELETE',
            url: 'http://localhost:8080/delete/' + book.b_id

        }).then(function successCallback(response) {

            alert("book has been deleted Successfully");
            var index = $scope.users.indexOf(book);
            $scope.books.splice(index, 1);

        }, function errorCallback(response) {

            alert("Error. while deleting book Try Again!");

        });

    };
});

app.controller('updatecontroller', function($scope, $http) {
    // console.log("hi");
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