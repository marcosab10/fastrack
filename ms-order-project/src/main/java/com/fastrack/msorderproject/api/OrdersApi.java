package com.fastrack.msorderproject.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.fastrack.msorderproject.dto.ExceptionResponse;
import com.fastrack.msorderproject.dto.OrderDto;
import com.fastrack.msorderproject.models.StatusEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "orders")
public interface OrdersApi {

    @ApiOperation(value = "create", nickname = "createUsingPOST", notes = "", response = OrderDto.class, tags={ "order-rest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "Created", response = OrderDto.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ExceptionResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ExceptionResponse.class) })
    
    @PostMapping(value = "/orders",
        produces = { "*/*" }, 
        consumes = { "application/json" })
    ResponseEntity<OrderDto> createUsingPOST(@ApiParam(value = "dto" ,required=true )  @RequestBody @Validated OrderDto body, UriComponentsBuilder uriBuilder
);


    @ApiOperation(value = "delete", nickname = "deleteUsingDELETE", notes = "", response = OrderDto.class, tags={ "order-rest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderDto.class),
        @ApiResponse(code = 204, message = "No Content", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ExceptionResponse.class) })
    @DeleteMapping(value = "/orders/{id}",
        produces = { "*/*" })
    ResponseEntity<OrderDto> deleteUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id, UriComponentsBuilder uriBuilder
);


    @ApiOperation(value = "findById", nickname = "findByIdUsingGET", notes = "", response = OrderDto.class, tags={ "order-rest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderDto.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ExceptionResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ExceptionResponse.class) })
    @GetMapping(value = "/orders/{id}",
        produces = { "*/*" })
    ResponseEntity<OrderDto> findByIdUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id
);


    @ApiOperation(value = "list", nickname = "listUsingGET", notes = "", response = OrderDto.class, responseContainer = "List", tags={ "order-rest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ExceptionResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ExceptionResponse.class) })
    @GetMapping(value = "/orders",
        produces = { "*/*" })
    ResponseEntity<List<OrderDto>> listUsingGET();


    @ApiOperation(value = "search", nickname = "searchUsingGET", notes = "", response = OrderDto.class, responseContainer = "List", tags={ "order-rest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderDto.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized", response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ExceptionResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ExceptionResponse.class) })
    @GetMapping(value = "/orders/search",
        produces = { "*/*" })
    ResponseEntity<List<OrderDto>> searchUsingGET(@ApiParam(value = "")  @RequestParam(value = "max_total", required = false) String maxTotal
,@ApiParam(value = "") @RequestParam(value = "min_total", required = false) String minTotal
,@ApiParam(value = "") @RequestParam(value = "status", required = false) StatusEnum status
,@ApiParam(value = "") @RequestParam(value = "q", required = false) String q
);


    @ApiOperation(value = "update", nickname = "updateUsingPUT", notes = "", response = OrderDto.class, tags={ "order-rest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderDto.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ExceptionResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ExceptionResponse.class) })
    @PutMapping(value = "/orders/{id}",
        produces = { "*/*" }, 
        consumes = { "application/json" })
    ResponseEntity<OrderDto> updateUsingPUT(@ApiParam(value = "dto" ,required=true )  @RequestBody @Validated OrderDto body
,@ApiParam(value = "id",required=true) @PathVariable("id") Long id
);


}
