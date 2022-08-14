import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-focus-button',
  templateUrl: './focus-button.component.html',
  styleUrls: ['./focus-button.component.scss']
})
export class FocusButtonComponent implements OnInit {

  @Input() class?: string;

  @Input() id?: string;

  @Input() title?: string;

  @Output() click: EventEmitter<void> = new EventEmitter();

  constructor() {
  }

  ngOnInit(): void {
  }

  handleClick(): void {
    this.click.emit();
  }

}
