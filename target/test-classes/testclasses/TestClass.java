package testclasses;

import com.topekalabs.pg4j.annotations.PGClass;
import com.topekalabs.pg4j.annotations.PGField;
import com.topekalabs.pg4j.annotations.PGFieldType;
import com.topekalabs.pg4j.annotations.PGType;
import com.topekalabs.pg4j.annotations.PGTypeParam;
import com.topekalabs.pg4j.annotations.PGTypeParams;
import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.chars.CharArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Topeka Labs
 */
@PGClass(typeParams={@PGTypeParams(typeParams=
                                  {@PGTypeParam(name="T",
                                                types={PGType.ALL})
                                  })
                    })
public class TestClass<T>
{
    //No annotation required.
    private T value;
    
    @PGField(types=
            {@PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.BOOL})
                                        })
                          },
                          clazz=BooleanArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.BYTE})
                                        })
                          },
                          clazz=ByteArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.CHAR})
                                        })
                          },
                          clazz=CharArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.SHORT})
                                        })
                          },
                          clazz=ShortArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.INT})
                                        })
                          },
                          clazz=IntArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.LONG})
                                        })
                          },
                          clazz=LongArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.FLOAT})
                                        })
                          },
                          clazz=FloatArrayList.class),
             @PGFieldType(typeParams={
                          @PGTypeParams(typeParams=
                                        {@PGTypeParam(name="T",
                                                      types={PGType.DOUBLE})
                                        })
                          },
                          clazz=DoubleArrayList.class)
            })
    private List<T> genList = new ArrayList<>();
    
    public TestClass()
    {
    }
}
