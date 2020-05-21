export default class Screen {
  private static isFull: boolean = false;

  public static init (): boolean {
    this.isFull = !!(document.webkitIsFullScreen || document.mozFullScreen || document.msFullscreenElement || document.fullscreenElement);
    return this.isFull;
  }

  public static handleFullScreen (): boolean {
    if (this.isFull) this.exitFull();
    else this.full();
    return this.isFull;
  }

  private static full () {
    if (this.isFull === false) {
      this.isFull = true;
      const element = document.documentElement;
      if (element.requestFullscreen) {
        element.requestFullscreen();
      } else if (element.webkitRequestFullScreen) {
        element.webkitRequestFullScreen();
      } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
      } else if (element.msRequestFullscreen) {
        element.msRequestFullscreen(); // IE11
      }
    }
  }

  private static exitFull () {
    if (this.isFull) {
      this.isFull = false;
      if (document.exitFullscreen) {
        document.exitFullscreen();
      } else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
      } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
      } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
      }
    }
  }
}
