

export default {
  state: {
    // Add new state properties here
    // 相当于router用于切换状态
        status: "matching",
        socket: null,
        opponent_username: "",
        opponent_photo: "",
        gamemap: null,
  },
  getters: {
  },
  mutations: {
    updateSocket(state, socket) {
        state.socket = socket;
    },
    updateOpponent(state, opponent) {
        state.opponent_username = opponent.username;
        state.opponent_photo = opponent.photo;
    },
    updateStatus(state, status) {
        state.status = status
    },
    updateGamemap(state,gamemap) {
        state.gamemap = gamemap
    }
  },
  actions: {
  },
  modules: {
  }
}
