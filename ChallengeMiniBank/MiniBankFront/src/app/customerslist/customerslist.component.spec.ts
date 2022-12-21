import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerslistComponent } from './customerslist.component';

describe('CustomerslistComponent', () => {
  let component: CustomerslistComponent;
  let fixture: ComponentFixture<CustomerslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerslistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
