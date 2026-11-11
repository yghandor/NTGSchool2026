import { Component } from '@angular/core';
import { SplitterModule } from 'primeng/splitter';
import { Shape } from './shape/shape';

@Component({
  imports: [SplitterModule, Shape],
  selector: 'NTG-process',
  styleUrl: './process.css',
  templateUrl: './process.html',
})
export class Process {
  AvilableShapList: ShapeInfo[] = [{ text: 'Rect', width: 100, height: 50, x: 10, y: 10 }];

  ShapeList: ShapeInfo[] = [];

  SelectShapeForMove: ShapeInfo | null = null;
  LastMousePositionX: number = 0;
  LastMousePositionY: number = 0;

  HandleShapeSelectation(e: MouseEvent, s: ShapeInfo) {
    this.SelectShapeForMove = s;
    this.LastMousePositionX = e.x;
    this.LastMousePositionY = e.y;
    e.preventDefault();
  }

  protected HandleMouseMove(e: MouseEvent) {
    e.preventDefault();
    if (this.SelectShapeForMove !== null) {
      let dx = e.x - this.LastMousePositionX;
      let dy = e.y - this.LastMousePositionY;

      this.SelectShapeForMove.x += dx;
      this.SelectShapeForMove.y += dy;

      this.LastMousePositionX = e.x;
      this.LastMousePositionY = e.y;
    }
  }

  SelectedShapForDrop: ShapeInfo | null = null;

  protected HandleDrop(e: DragEvent, drawingArea: HTMLDivElement) {
    e.preventDefault();

    let rec = drawingArea.getBoundingClientRect();

    if (this.SelectedShapForDrop != null) {
      let newShap: ShapeInfo = { ...this.SelectedShapForDrop };

      newShap.text = 'Text #' + (this.ShapeList.length + 1);
      newShap.width = 250;
      newShap.height = 150;

      newShap.y = e.y - rec.top;
      newShap.x = e.x - rec.left;

      this.ShapeList.push(newShap);
    }
  }
}
