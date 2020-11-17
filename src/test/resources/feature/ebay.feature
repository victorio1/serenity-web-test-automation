Feature: funcionalidad de Realizar pedido

  @pedido1
  Scenario Outline: Realizar pedido de zapatos en ebay
    Given buscamos el producto <producto> por talla <talla> y marca <marca>
    And seleccionamos el orden <orden> para listar el producto
    When ordenamos los productos por nombre ascendente
    And ordenamos los precios en modo descendente
    Then validamos que los productos listados muestren la marca <marcaLista>

    Examples: 
      | producto | talla | marca | orden                            | marcaLista |
      | zapatos  |    10 | PUMA  | Precio + Envío: más bajo primero | Puma       |
