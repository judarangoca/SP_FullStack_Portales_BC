import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomersaddComponent } from './customersadd.component';

describe('CustomersaddComponent', () => {
  let component: CustomersaddComponent;
  let fixture: ComponentFixture<CustomersaddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomersaddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomersaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
