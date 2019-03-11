import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "hash",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "index",
      redirect: '/user-center'
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    },
    {
      path: "/login",
      name: "login",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/login.vue")
    },
    {
      path: "/user-center",
      name: "user-center",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/user-center.vue")
    },
    {
      path: "/billBookTypeList",
      name: "billBookTypeList",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/billBookTypeList.vue")
    },
    {
      path: "/billBook",
      name: "billBook",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/billBook.vue")
    }
  ]
});
