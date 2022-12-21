import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomersmodifyComponent } from './customersmodify.component';

describe('CustomersmodifyComponent', () => {
  let component: CustomersmodifyComponent;
  let fixture: ComponentFixture<CustomersmodifyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomersmodifyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomersmodifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
