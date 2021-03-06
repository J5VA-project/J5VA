app.controller("product-manager-ctrl", function($scope, $http) {
		$("#btn-add").click(function() {
			var check = true;

			$('.error_foodName').css('color', 'red');
			$('.error_foodName').css('font-style', 'italic');
			$('.error_foodPrice').css('color', 'red');
			$('.error_foodPrice').css('font-style', 'italic');
			$('.error_foodCategory').css('color', 'red');
			$('.error_foodCategory').css('font-style', 'italic');
			$('.error_foodSize').css('color', 'red');
			$('.error_foodSize').css('font-style', 'italic');

			if ($('#foodname').val() == '') {
				$('.error_foodName').html('X Food Name not null');
				check = false;
			}
			if ($('#foodprice').val() == '') {
				$('.error_foodPrice').html('X Food Price not null');
				check = false;
			}
			if ($("select[name=category]").val() == '? undefined:undefined ?') {
				$('.error_foodCategory').html('X Category not null');
				check = false;
			}
			if ($("select[name=size]").val() == '? undefined:undefined ?') {
				$('.error_foodSize').html('X Size not null');
				check = false;
			}
			if (check == true) {
				$scope.create();
			}
		});

	$scope.foods = [];
	$scope.cates = [];
	$scope.sizes = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load foods
		$http.get("/rest/food-detail").then(resp => {
			$scope.foods = resp.data;
		});
		//load categories
		$http.get("/rest/category").then(resp => {
			$scope.cates = resp.data;
		});
		//load size
		$http.get("/rest/size").then(resp => {
			$scope.sizes = resp.data;
		});
		$scope.random = Math.floor((Math.random() * 99999));
		$scope.form.food_id = $scope.random;
	}

	//khởi đầu
	$scope.initialize();


	//xóa form
	$scope.reset = function() {
		$scope.random = Math.floor((Math.random() * 99999));
		$scope.form = {
			food_id: $scope.random,
			status: true,
			image_title1: 'image-df.jpg',
			image_title2: 'image-df.jpg',
			food: {
				photo: 'image-df.jpg'
			},
		};
	}

	$scope.reset();

	//hiển thị lên form
	$scope.edit = function(food) {
		$scope.form = angular.copy(food);
	}

	//thêm sản phẩm mới
	$scope.create = function() {
		var food = angular.copy($scope.form);
		$http.post(`/rest/food-detail`, food).then(resp => {
			$scope.foods.push(resp.data);
			$scope.reset();
			$scope.initialize();
			alert("Thêm mới food thành công!");
		}).catch(error => {
			alert("Lỗi thêm mới food!");
			console.log("Error", error);
		});
	}


	//thêm sản phẩm mới
	$scope.update = function() {
		var food = angular.copy($scope.form);
		$http.put(`/rest/food-detail/${food.food_id}`, food).then(resp => {
			var index = $scope.foods.findIndex(p => p.food_id == food.food_id);
			$scope.foods[index] = food;
			$scope.initialize();
			alert("Cập nhật food thành công!");
		}).catch(error => {
			alert("Lỗi cập nhật food!");
			console.log("Error", error);
		});
	}

	//xóa sản phẩm
	$scope.delete = function(food) {
		$http.delete(`/rest/food/${food.food_id}`).then(resp => {
			var index = $scope.foods.findIndex(p => p.food_id == food.food_id);
			$scope.foods.splice(index, 1);
			$scope.reset();
			$scope.initialize();
			alert("Xóa food thành công!");
		}).catch(error => {
			alert("Lỗi xóa food!");
			console.log("Error", error);
		});
	}
	//Upload hình
	$scope.imageFoodChange = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.food.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	//Upload hình
	$scope.imageFoodDetail1 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image_title1 = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	//Upload hình
	$scope.imageFoodDetail2 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image_title2 = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		food: 20,
		get foods() {
			var start = this.page * this.food;
			return $scope.foods.slice(start, start + this.food);
		},
		get count() {
			return Math.ceil(1.0 * $scope.foods.length / this.food);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

	// $scope index lưu trữ tab hiện tại, ban đầu gán nó = tab1
	$scope.current_tab = 1;

	// Hàm đổi tab
	$scope.changeTab = function(index) {
		$scope.current_tab = index;
	};

	// Hàm kiểm tra có phải tab hiện tại hay không
	// hàm này sẽ trả về true/false và dùng kết hợp
	// với ng-class để active menu
	$scope.isActiveTab = function(index) {
		return index === $scope.current_tab;
	};
});