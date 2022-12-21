import { TestBed } from '@angular/core/testing';

import { CustomersserviceService } from './customersservice.service';

describe('CustomersserviceService', () => {
  let service: CustomersserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomersserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
