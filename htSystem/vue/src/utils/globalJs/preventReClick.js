export default {
  install (Vue) {
    // 防重复点击(指令实现)
    Vue.directive('preventReClick', {
      //el:指令所绑定的元素，可以直接用操作dom
      inserted (el, binding) {
        el.addEventListener('click', () => {
          if (!el.disabled) {
            //置为点击失效
            el.disabled = true
            setTimeout(() => {
              el.disabled = false
              //bing.value为绑定的属性值，即在 v-preventReClick=5000 中5000即为binding.value，5000可以不写，不写的话默认即为3000
            }, binding.value*1000 || 2000)
          }
        })
      }
    })
  //按钮根据权限显示和影藏
  Vue.directive('has', {
    inserted: function (el, binding) {
      if (!permissionJudge(binding.value)) {
        el.parentNode.removeChild(el);
      }
  
      function permissionJudge(value) {
        let list = JSON.parse(sessionStorage.getItem('buttonUrlList'));
        if(!list){
          return false;
        }
        for (let i=0;i<list.length;i++) {
          if (list[i] === value) {
            return true;
          }
        }
        return false;
      }
    }
  });
    

  }


}
