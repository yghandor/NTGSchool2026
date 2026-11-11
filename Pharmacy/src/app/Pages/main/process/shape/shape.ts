import { Component, Input } from '@angular/core';

@Component({
  imports: [],
  selector: 'NTG-shape',
  styleUrl: './shape.css',
  templateUrl: './shape.html',
})
export class Shape {
  textWidth = 0;

  @Input()
  x: number = 0;
  @Input()
  y: number = 0;

  @Input('shapeInfo')
  set setshapeInfo(s: ShapeInfo) {
    this.shapeInfo = s;
    this.textWidth = this.calcTextWidth(this.shapeInfo.text);
  }
  shapeInfo!: ShapeInfo;

  private calcTextWidth(text: string | undefined) {
    let canvas = document.createElement('canvas');
    let ctx = canvas.getContext('2d');
    if (ctx) {
      return ctx.measureText(text || '').width;
    }

    return 0;
  }
}
