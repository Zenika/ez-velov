import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FocusButtonComponent } from './focus-button.component';

describe('FocusButtonComponent', () => {
  let component: FocusButtonComponent;
  let fixture: ComponentFixture<FocusButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FocusButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FocusButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
