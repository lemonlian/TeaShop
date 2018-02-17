$(function() {
	$.ajaxSetup({
		dataType: 'json',
		error() {
			console.log('请求失败')
		}
	})
	let ajax = {
		// 获取用户信息
		getUserInfo() {
			const url = '/user/userDetailedById';
			$.ajax({
				url,
				success(data) {
					if (data.code == 1) {
						$('#userId').html(data.data[0].user_id)
						$('#userName').html(data.data[0].user_name)
						$('#leftMoney').html(data.data[0].user_balance)
					}
				}
			})
		},
		// 获取购物车商品
		getShoppingCart() {
			const url = '/order/getOrderNotDealByUserId';
			const promise = new Promise((resolve, reject) => {
				$.ajax({
					url,
					success(data) {
						if (data.code == 1) {
							let str = ''
							for (let i = 0; i < data.data.length; i++) {
								str += `<tr>
									<td>${data.data[i].order_id}</td>
									<td>${data.data[i].o_tea_id}</td>
									<td>${data.data[i].tea_buy_num}</td>
									<td>${data.data[i].order_price}</td>
 									<td>
										<button class = "btn btn-primary buyBtn" type ="button">购买</button>
										<button class = "btn btn-info modefiedBtn" type ="button" data-toggle="modal" data-target="#modefiedModal">修改数量</button>
										<button class = "btn btn-danger cancelBtn" type ="button">取消</button> 
									</td> 
									</tr>`
							}
							$('#shopCart').append(str);
							resolve('done')
						}
					}
				})
			})
			return promise;
		},
		// 获取已购买订单
		getBoughtOrder(){
			const url = '/order/getOrderDealByUserId';
			$.ajax({
				url,
				success(data){
					if (data.code == 1) {
						let str = ''
						for (let i = 0; i < data.data.length; i++) {
							str += `<tr>
										<td>${data.data[i].order_id}</td>
										<td>${data.data[i].o_tea_id}</td>
										<td>${data.data[i].tea_buy_num}</td>
										<td>${data.data[i].order_price}</td>
										<td>已购买</td> 
									</tr>`
						}
						$('#boughtOrder').append(str);
					}
				}
			})
		},
		// 充值
		topUp(userBalance, userId) {
			const url = '/user/userRechargeBalance';
			$.ajax({
				url,
				type:'post',
				data: {
					userBalance,
					userId
				},
				success(data) {
					if (data.code == 1) {
						let leftMoney = $('#leftMoney').html();
						$('#leftMoney').html(Number(leftMoney) + Number(userBalance));
						alert('充值成功');
						$('#chargeModal').hide().removeClass('in');
						$('.modal-backdrop').hide();
					}
				}
			})
		},
		// 购买购物车中的商品
		buyTea(orderId,teaPrice){
			const url="/order/payForOrder";
			const promise = new Promise((resolve,reject)=>{
				$.ajax({
					url,
					type:'post',
					data:{orderId},
					success(data){
						if(data.code == 1){
							alert('支付成功');
							let leftMoney = $('#leftMoney').html() - teaPrice;
							$('#leftMoney').html(leftMoney)
							resolve('done');
						}else{
							alert(data.msg);
						}
					}
				})
			})
			return promise;
		},
		// 修改数量
		modefiedNum(orderId,teaBuyNum){
			const url='/order/updateOrder'
			$.ajax({
				url,
				type:'post',
				data:{orderId,teaBuyNum},
				success(data){
					if(data.code==1){
						$curTr.find('td:eq(2)').html(teaBuyNum)
						$('#modefiedModal').hide().removeClass('in');
						$('.modal-backdrop').hide();
					}else{
						alert('修改失败')
					}
				}
			})
		},
		// 取消订单
		cancelOrder(orderId){
			const url='/order/deleteOrder';
			const promise = new Promise((resolve,reject)=>{
				$.ajax({
					url,
					data:{orderId},
					type:'post',
					success(data){
						if(data.code==1){
							resolve('done');
						}else{
							reject('failed');
						}
					}
				})
			})
			return promise;
		},
		getDealedOrder(){
			const url = '/order/getAllOrderDetail'
			$.ajax({
				url,
				success(data){
					if (data.code == 1) {
						let str = ''
						for (let i = 0; i < data.data.length; i++) {
							str += `<tr>
										<td>${data.data[i].order_id}</td>
										<td>${data.data[i].o_user_id}</td>
										<td>${data.data[i].o_tea_id}</td>
										<td>${data.data[i].tea_buy_num}</td>
										<td>${data.data[i].order_price}</td>
										<td>已成交</td> 
									</tr>`
						}
						$('#dealedOrder tbody').append(str);
					}
				}
			})
		}
	}

	$('#checkCharge').click(function() {
		let userBalance = $('#money').val(),
			userId = $('userId').html();
		if (userBalance == '' || !/\d/g.test(userBalance)) {
			alert('请输入充值金额，只能输入数字');
		} else {
			ajax.topUp(userBalance, userId);
		}
	})
	$('#checkModefied').click(function(){
		let selectNum=$('#modefiedModal').find('option:selected').val()
		ajax.modefiedNum(orderId,selectNum)
	})
	let $curTr,orderId;
	ajax.getShoppingCart().then((value)=>{
		$('.buyBtn').click(function(){
			let $item = $(this).parents('tr')
			let orderId=$item.find('td:eq(0)').html(),
				teaPrice=$item.find('td:eq(3)').html();
			ajax.buyTea(orderId,teaPrice).then((value)=>{
				$item.remove();
				$item.find('td:last').html('已购买');
				$('#boughtOrder tbody').append($item)
			})
		})
		$('.modefiedBtn').click(function(){
			$curTr = $(this).parents('tr')
			orderId=$curTr.find('td:first').html();
		})
		$('.cancelBtn').click(function(){
			let $item = $(this).parents('tr')
			let orderId = $item.find('td:first').html();
			ajax.cancelOrder(orderId).then((value)=>{
				$item.remove();
			})
		})
	}).catch((error)=>{
		console.log(error)
	});
	// 管理员
	ajax.getUserInfo();
	ajax.getBoughtOrder();
	// 用户
	ajax.getDealedOrder();
	console.log(CookieUtil.get('state'))
	if(CookieUtil.get('state')==1){ // 显示用户
		$('#managerView').remove();
	}else if(CookieUtil.get('state')==2){ // 显示管理员界面
		$('#userView').remove();
	}
})