import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ApplicationMenu } from './application-menu.component';

describe('Menu', () => {
  let component: ApplicationMenu;
  let fixture: ComponentFixture<ApplicationMenu>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplicationMenu],
    }).compileComponents();

    fixture = TestBed.createComponent(ApplicationMenu);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
